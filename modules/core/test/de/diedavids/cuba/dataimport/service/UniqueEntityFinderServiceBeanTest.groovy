package de.diedavids.cuba.dataimport.service

import com.haulmont.cuba.core.global.AppBeans
import de.diedavids.cuba.dataimport.AbstractDbIntegrationTest
import de.diedavids.cuba.dataimport.entity.*
import de.diedavids.cuba.dataimport.entity.example.MlbTeam
import org.junit.Before
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat

class UniqueEntityFinderServiceBeanTest extends AbstractDbIntegrationTest {


    protected UniqueEntityFinderService sut

    protected ImportConfiguration importConfiguration

    @Before
    void setUp() throws Exception {


        super.setUp()

        sut = AppBeans.get(UniqueEntityFinderService.NAME)

        importConfiguration = new ImportConfiguration(
                entityClass: 'ddcdi$MlbPlayer',
                importAttributeMappers: [
                        new ImportAttributeMapper(entityAttribute: 'ddcdi$MlbPlayer.name', fileColumnAlias: 'name', fileColumnNumber: 0),
                        new ImportAttributeMapper(entityAttribute: 'ddcdi$MlbPlayer.height', fileColumnAlias: 'height', fileColumnNumber: 2),
                ],
                transactionStrategy: ImportTransactionStrategy.SINGLE_TRANSACTION
        )


        clearTable("DDCDI_MLB_PLAYER")
        clearTable("DDCDI_MLB_TEAM")
    }


    @Test
    void "findUniqueEntity finds an existing entity that fulfills the unique configurations with a given entity"() {

        MlbTeam existingBalTeam = createAndStoreMlbTeam('Baltimore Orioles', 'BAL')
        MlbTeam entityToBeImported = createMlbTeam('Baltimore Orioles', 'BAL')

        List<UniqueConfiguration> uniqueConfigurations = createCodeAndNameUniqueConfiguration()

        MlbTeam existingUniqueEntityResult = sut.findEntity(entityToBeImported, uniqueConfigurations) as MlbTeam

        assertThat(existingUniqueEntityResult).isNotNull()
        assertThat(existingUniqueEntityResult).isEqualTo(existingBalTeam)

        cont.deleteRecord(existingBalTeam)
    }


    @Test
    void "findUniqueEntity finds no entity that fulfills the unique configurations with a given entity"() {

        MlbTeam existingBalTeam = createAndStoreMlbTeam('Baltimore Orioles', 'BAL')
        MlbTeam entityToBeImported = createMlbTeam('Other Baltimore Team', 'BAL')

        List<UniqueConfiguration> uniqueConfigurations = createCodeAndNameUniqueConfiguration()

        MlbTeam existingUniqueEntityResult = sut.findEntity(entityToBeImported, uniqueConfigurations) as MlbTeam

        assertThat(existingUniqueEntityResult).isNull()

        cont.deleteRecord(existingBalTeam)
    }

    private List<UniqueConfiguration> createCodeAndNameUniqueConfiguration() {
        Collection<UniqueConfiguration> uniqueConfigurations = [
                new UniqueConfiguration(
                        entityAttributes: [
                                new UniqueConfigurationAttribute(entityAttribute: 'code'),
                                new UniqueConfigurationAttribute(entityAttribute: 'name'),
                        ],
                        policy: UniquePolicy.SKIP
                )
        ]
        uniqueConfigurations
    }

    private MlbTeam createAndStoreMlbTeam(String name, String code) {
        dataManager.commit(createMlbTeam(name, code))
    }

    private MlbTeam createMlbTeam(String name, String code) {
        MlbTeam existingBalTeam = metadata.create(MlbTeam)
        existingBalTeam.name = name
        existingBalTeam.code = code
        existingBalTeam
    }

}
