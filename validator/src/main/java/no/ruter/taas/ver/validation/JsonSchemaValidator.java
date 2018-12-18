package no.ruter.taas.ver.validation;

import static no.ruter.taas.ver.validation.DefaultValidationConfig.RESOLUTION_SCOPE;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

class JsonSchemaValidator {

  private final String jsonSchemaLocation;
  private SchemaLoader schemaLoader;

  JsonSchemaValidator(String jsonSchemaLocation) {
    this.jsonSchemaLocation = jsonSchemaLocation;
  }

  static JSONObject getJsonObject(String jsonDataLocation) throws IOException {
    String data = readFile(jsonDataLocation, Charset.defaultCharset());
    return new JSONObject(new JSONTokener(data));
  }

  private static String readFile(String path, Charset encoding) throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded, encoding);
  }

  //
  // Validation Methods
  //
  void validate(JSONObject jsonData) throws IOException {
    initializeSchemaLoader();
    Schema schema = schemaLoader.load().build();
    schema.validate(jsonData);
  }

  void validateAndPrintInfo(JSONObject jsonData) throws IOException {
    try {
      validate(jsonData);
      System.out.println("Validation successful");

    } catch (ValidationException e) {
      System.out.println(e.getMessage());
      e.getCausingExceptions().stream()
          .map(ValidationException::getMessage)
          .forEach(System.out::println);
      System.out.println("Errors as JSON: " + e.toJSON());
    }
  }

  private void initializeSchemaLoader() throws IOException {
    if (this.schemaLoader == null) {
      JSONObject rawSchema = getJsonObject(jsonSchemaLocation);

      this.schemaLoader = SchemaLoader.builder()
          .schemaJson(rawSchema)
          .draftV7Support()
          .resolutionScope(RESOLUTION_SCOPE)
          .build();

    }
  }
}