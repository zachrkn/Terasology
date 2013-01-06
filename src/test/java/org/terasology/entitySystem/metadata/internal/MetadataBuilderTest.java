package org.terasology.entitySystem.metadata.internal;

import org.junit.Before;
import org.junit.Test;
import org.terasology.entitySystem.metadata.ClassMetadata;
import org.terasology.entitySystem.metadata.FieldMetadata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Immortius
 */
public class MetadataBuilderTest {

    private MetadataBuilder metadataBuilder;

    @Before
    public void setup() {
        this.metadataBuilder = new MetadataBuilder();
    }

    @Test
    public void requireDefaultConstructor() {
        assertNull(metadataBuilder.build(NoDefaultConstructor.class));
    }

    @Test
    public void trivialMetadata() {
        ClassMetadata<Trivial> metadata = metadataBuilder.build(Trivial.class);
        assertNotNull(metadata);
        assertEquals(0, metadata.size());
    }

    @Test
    public void testPrivateField() {
        ClassMetadata<PrivateField> metadata = metadataBuilder.build(PrivateField.class);
        assertNotNull(metadata);
        assertEquals(1, metadata.size());
        FieldMetadata fieldMetadata = metadata.getField("name");
        assertNotNull(fieldMetadata);
        assertEquals(String.class, fieldMetadata.getType());
        assertEquals("name", fieldMetadata.getName());
        assertNotNull(metadata.newInstance());
    }

    @Test
    public void testInheritsFields() {
        ClassMetadata<Inheriting> metadata = metadataBuilder.build(Inheriting.class);
        assertNotNull(metadata);
        assertEquals(2, metadata.size());
        assertNotNull(metadata.getField("name"));
        assertNotNull(metadata.getField("value"));
        assertNotNull(metadata.newInstance());
    }

    private static class NoDefaultConstructor {
        public NoDefaultConstructor(String name) {

        }
    }

    public static class Trivial {
    }

    public static class PrivateField {
        private String name;

        private PrivateField() {}

        public PrivateField(String name) {
            this.name = name;
        }

        public String whatName() {
            return name;
        }
    }

    public static class Inheriting extends PrivateField {
        private String value;

        private Inheriting() {
        }

        public Inheriting(String name, String value) {
            super(name);
            this.value = value;
        }

        public String whatValue() {
            return value;
        }
    }
}