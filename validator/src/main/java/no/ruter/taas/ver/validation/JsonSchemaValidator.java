package no.ruter.taas.ver.validation;

import static no.ruter.taas.ver.validation.DefaultValidationConfig.EQUIPMENT_SCHEMA;
import static no.ruter.taas.ver.validation.DefaultValidationConfig.EQUIPMENT_SCHEMA_LOCATION;
import static no.ruter.taas.ver.validation.DefaultValidationConfig.INVENTORY_SCHEMA;
import static no.ruter.taas.ver.validation.DefaultValidationConfig.INVENTORY_SCHEMA_LOCATION;
import static no.ruter.taas.ver.validation.DefaultValidationConfig.VEHICLE_LIST_SCHEMA;
import static no.ruter.taas.ver.validation.DefaultValidationConfig.VEHICLE_LIST_SCHEMA_LOCATION;
import static no.ruter.taas.ver.validation.DefaultValidationConfig.VEHICLE_SCHEMA;
import static no.ruter.taas.ver.validation.DefaultValidationConfig.VEHICLE_SCHEMA_LOCATION;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
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
    String data = readFile(jsonDataLocation);
    return new JSONObject(new JSONTokener(data));
  }

  private static String readFile(String path) throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded, StandardCharsets.UTF_8);
  }
  //
  // Validation Methods
  //
  void validate(JSONObject jsonData) throws IOException, URISyntaxException {
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

    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }

  private void initializeSchemaLoader() throws IOException, URISyntaxException {
    if (this.schemaLoader == null) {
      JSONObject rawSchema = getJsonObject(jsonSchemaLocation);
      JSONObject rawVehicleSchema = getJsonObject(VEHICLE_SCHEMA_LOCATION);
      JSONObject rawVehicleListSchema = getJsonObject(VEHICLE_LIST_SCHEMA_LOCATION);
      JSONObject rawEquipmentSchemaSchema = getJsonObject(EQUIPMENT_SCHEMA_LOCATION);
      JSONObject rawInventorySchema = getJsonObject(INVENTORY_SCHEMA_LOCATION);

      this.schemaLoader = SchemaLoader.builder()
          .registerSchemaByURI(new URI(VEHICLE_SCHEMA), rawVehicleSchema)
          .registerSchemaByURI(new URI(VEHICLE_LIST_SCHEMA), rawVehicleListSchema)
          .registerSchemaByURI(new URI(EQUIPMENT_SCHEMA), rawEquipmentSchemaSchema)
          .registerSchemaByURI(new URI(INVENTORY_SCHEMA), rawInventorySchema)
          .schemaJson(rawSchema)
          .draftV7Support()
          .build();
    }
  }
}