package br.com.takeaguide.takeaguide;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class ArchitectureTest {

    @Test
    public void repositoriesShouldOnlyBeAccessedByServices() {
        ArchRule rule = classes()
            .that().resideInAPackage("..domain.repositories..")
            .should().onlyBeAccessed().byAnyPackage("..application.services..")
            .allowEmptyShould(true); // Permitir a ausência de classes para evitar falhas

        rule.check(new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("br.com.takeaguide.takeaguide"));
    }

    @Test
    public void servicesShouldOnlyBeAccessedByControllers() {
        ArchRule rule = classes()
            .that().resideInAPackage("..application.services..")
            .should().onlyBeAccessed().byAnyPackage("..adapters.controllers..")
            .allowEmptyShould(true);

        rule.check(new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("br.com.takeaguide.takeaguide"));
    }

    @Test
    public void validateLayeredArchitecture() {
        ArchRule layeredArchitecture = Architectures.layeredArchitecture()
            .layer("Controllers").definedBy("br.com.takeaguide.takeaguide.adapters.controllers..")
            .layer("Services").definedBy("br.com.takeaguide.takeaguide.application.services..")
            .layer("Repositories").definedBy("br.com.takeaguide.takeaguide.domain.repositories..")
            .layer("Entities").definedBy("br.com.takeaguide.takeaguide.domain.entities..")
            .whereLayer("Controllers").mayOnlyBeAccessedByLayers("Services")
            .whereLayer("Services").mayOnlyBeAccessedByLayers("Repositories", "Entities")
            .whereLayer("Repositories").mayOnlyBeAccessedByLayers("Entities")
            .allowEmptyShould(true);

        layeredArchitecture.check(new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("br.com.takeaguide.takeaguide"));
    }
}
