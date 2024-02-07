package mn.ser.security

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonSetter
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Slf4j
import io.micronaut.core.annotation.Introspected

@Slf4j
@CompileStatic
@Introspected(accessKind = Introspected.AccessKind.FIELD)
@EqualsAndHashCode
class Pet {
    private String name
    private String type

    /**
     * Ignored on deserialization when the method is not called {@code setName}.
     * The default Groovy setter is used instead, causing a conversion error.
     */
    @JsonSetter('name')
    void deserializeName(def value) {
        if (value instanceof String) {
            this.name = value
        } else {
            log.warn("Not a string, ignoring field")
        }
    }

    /**
     * Ignored on serialization when the method is not called {@code getType}.
     */
    @JsonGetter('type')
    String serializeType() {
        if (type == 'dog') {
            return type.toUpperCase()
        }
        return type
    }

    void setName(String name) {
        this.name = name
    }

    void setType(String type) {
        this.type = type
    }


    String getName() {
        return name
    }

    String getType() {
        return type
    }
}

