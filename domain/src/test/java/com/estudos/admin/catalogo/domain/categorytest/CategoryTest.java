package com.estudos.admin.catalogo.domain.categorytest;

import com.estudos.admin.catalogo.domain.category.Category;
import com.estudos.admin.catalogo.domain.exceptions.DomainException;
import com.estudos.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void givenAValidParams_whenCallNewCategory_thenInstantiateACategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());


    }

    @Test
    public void givenAnInvalidNullName_whenCallNewCategory_thenInstantiateACategory() {
        final String expectedName = null;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'name' Should not be null";
        final var expectedErrorCount = 1;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());


    }

    @Test
    public void givenAnInvalidEmptyName_whenCallNewCategory_thenInstantiateACategory() {
        final String expectedName = " ";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'name' Should not be empty";
        final var expectedErrorCount = 1;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());


    }

    @Test
    public void givenAnInvalidNameLengthLessThan3_whenCallNewCategory_thenInstantiateACategory() {
        final String expectedName = "Fi ";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'name' must be between 3 and 255 character";
        final var expectedErrorCount = 1;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());


    }

    @Test
    public void givenAnInvalidNameLengthMoreThan3_whenCallNewCategory_thenInstantiateACategory() {
        final String expectedName = """
                        Nesta arriscada aventura, Gato de Botas e Kitty vão contar com a ajuda - contra seus mais 
                aguçados instintos – do ratazanas vira-lata, tagarela e incansavelmente otimista, Perro. 
                Juntos, nosso trio de heróis terá que andar um passo à frente de Cachinhos Dourados e uma 
                surpreendente versão dos Três Ursos, de umPequeno João Trombeta nem tão pequeno assim, e do 
                aterrorizante caçador de recompensas, Lobo Mau
                """;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'name' must be between 3 and 255 character";
        final var expectedErrorCount = 1;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmptyDescription_whenCallNewCategory_thenInstantiateACategor() {
        final var expectedName = "Filmes";
        final var expectedDescription = " ";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(()-> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAvalidFalseIsActive_whenCallNewCategory_thenInstantiateACategor() {
        final var expectedName = "Filmes";
        final var expectedDescription = " ";
        final var expectedIsActive = false;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(()-> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAnValidActivedCategory_whenCallDeactivate_thenReturnCategoryInactivated(){
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida ";
        final var expectedIsActive = false;
        final var aCategory = Category.newCategory(expectedName, expectedDescription, true);

        Assertions.assertDoesNotThrow(()-> aCategory.validate(new ThrowsValidationHandler()));

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        final var actualCategory = aCategory.deactivate();

        Assertions.assertDoesNotThrow(()-> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertEquals(createdAt,actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidInactivedCategory_whenCallActivate_thenReturnCategoryInactivated(){
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida ";
        final var expectedIsActive = true;
        final var aCategory = Category.newCategory(expectedName, expectedDescription, false);

        Assertions.assertDoesNotThrow(()-> aCategory.validate(new ThrowsValidationHandler()));

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();


        final var actualCategory = aCategory.activate();


        Assertions.assertDoesNotThrow(()-> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertEquals(createdAt,actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualCategory.getDeletedAt());
    }
}
