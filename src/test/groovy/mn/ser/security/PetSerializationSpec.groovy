package mn.ser.security

import io.micronaut.json.JsonMapper
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class PetSerializationSpec extends Specification {

    @Inject
    JsonMapper jsonMapper

    void "test serialization"() {
        when:
        Pet pet = new Pet(name: "Rex", type: "dog")
        String json = jsonMapper.writeValueAsString(pet)

        then:
        json == '{"name":"Rex","type":"DOG"}'
    }

    void "test deserialization"() {
        given:
        Pet expected = new Pet(name: "Rex", type: "dog")

        when:
        Pet pet = jsonMapper.readValue('{"name":"Rex","type":"dog"}', Pet)

        then:
        expected == pet
    }

    void "test deserialization malformed JSON"() {
        given:
        Pet expected = new Pet(name:  null, type: "dog")

        when:
        Pet pet = jsonMapper.readValue('{"name":["Rex"],"type":"dog"}', Pet)

        then:
        expected == pet
    }
}
