package no.ruter.taas.ver.validation;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonSchemaValidator {

  private final String jsonSchemaLocation;
  private SchemaLoader schemaLoader;

  public JsonSchemaValidator(String jsonSchemaLocation) {
    this.jsonSchemaLocation = jsonSchemaLocation;
  }

  //
  // Validation Methods
  //
  void validate(JSONObject jsonData) throws IOException {
    initializeSchemaLoader();

    Schema schema = schemaLoader.load().build();

    try {
      schema.validate(jsonData);

    } catch (ValidationException e) {
      System.out.println(e.getMessage());
      e.getCausingExceptions().stream()
          .map(ValidationException::getMessage)
          .forEach(System.out::println);
      System.out.println();
      System.out.println(e.toJSON());
    }
  }


  private void initializeSchemaLoader() throws IOException {
    if (this.schemaLoader == null) {
      JSONObject rawSchema = getJsonObject(jsonSchemaLocation);

      this.schemaLoader = SchemaLoader.builder()
          .schemaJson(rawSchema)
          .draftV7Support()
          .build();

    }
  }


  static String detectJsonSchemaType(String jsonDataLocation) throws IOException {
    JSONObject jsonObject = getJsonObject(jsonDataLocation);
    return detectJsonSchemaType(jsonObject);
  }

  private static String detectJsonSchemaType(JSONObject jsonObject) {
    System.out.println("Schema type detection is not implemented!");
    return null;
  }

  static JSONObject getJsonObject(String jsonDataLocation) throws IOException {
    String data = readFile(jsonDataLocation, Charset.defaultCharset());
//    System.out.println(data);
    return new JSONObject(new JSONTokener(data));
  }

  private static String readFile(String path, Charset encoding) throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded, encoding);
  }
}
