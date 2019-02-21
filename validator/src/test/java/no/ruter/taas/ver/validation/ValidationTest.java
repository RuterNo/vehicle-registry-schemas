package no.ruter.taas.ver.validation;

import static no.ruter.taas.ver.validation.DefaultValidationConfig.EQUIPMENT_SCHEMA_LOCATION;
import static no.ruter.taas.ver.validation.DefaultValidationConfig.EXAMPLES_PATH;
import static no.ruter.taas.ver.validation.DefaultValidationConfig.INVENTORY_SCHEMA_LOCATION;
import static no.ruter.taas.ver.validation.DefaultValidationConfig.VEHICLE_EXAMPLE;
import static no.ruter.taas.ver.validation.DefaultValidationConfig.VEHICLE_LIST_SCHEMA_LOCATION;
import static no.ruter.taas.ver.validation.DefaultValidationConfig.VEHICLE_SCHEMA_LOCATION;
import static no.ruter.taas.ver.validation.JsonSchemaValidator.getJsonObject;

import java.io.IOException;
import java.net.URISyntaxException;
import org.everit.json.schema.ValidationException;
import org.junit.BeforeClass;
import org.junit.Test;


public class ValidationTest {

//  private String resolutionScope = RESOLUTION_SCOPE_GITHUB;
//  private boolean local = false;

  @BeforeClass
  public static void setUp() {
    System.out.println("Working Directory = " + System.getProperty("user.dir"));


  }

  @Test
  public void validateVehicle() throws IOException, URISyntaxException {
    System.out.println("Initialising validator using schema: " + VEHICLE_SCHEMA_LOCATION);
    JsonSchemaValidator validator = new JsonSchemaValidator(VEHICLE_SCHEMA_LOCATION);

    System.out.println("Validating json data: " + VEHICLE_EXAMPLE);
    validator.validate(getJsonObject(VEHICLE_EXAMPLE));
  }

  @Test
  public void validateVehicleList() throws IOException, URISyntaxException {
    System.out.println("Initialising validator using schema: " + VEHICLE_LIST_SCHEMA_LOCATION);
    JsonSchemaValidator validator = new JsonSchemaValidator(VEHICLE_LIST_SCHEMA_LOCATION);

    String jsonLocation = EXAMPLES_PATH + "/vehicle_list.json";
    System.out.println("Validating json data: " + jsonLocation);
    validator.validate(getJsonObject(jsonLocation));
  }

  @Test
  public void validateInventory() throws IOException, URISyntaxException {
    System.out.println("Initialising validator using schema: " + INVENTORY_SCHEMA_LOCATION);
    JsonSchemaValidator validator = new JsonSchemaValidator(INVENTORY_SCHEMA_LOCATION);

    String jsonLocation = EXAMPLES_PATH + "/inventory.json";
    System.out.println("Validating json data: " + jsonLocation);
    validator.validate(getJsonObject(jsonLocation));
  }

  @Test
  public void validateEquipment() throws IOException, URISyntaxException {
    System.out.println("Initialising validator using schema: " + EQUIPMENT_SCHEMA_LOCATION);
    JsonSchemaValidator validator = new JsonSchemaValidator(EQUIPMENT_SCHEMA_LOCATION);

    String jsonLocation = EXAMPLES_PATH + "/equipment.json";
    System.out.println("Validating json data: " + jsonLocation);
    validator.validate(getJsonObject(jsonLocation));
  }

  @Test(expected = ValidationException.class)
  public void validationFailsForVehicleWithInventory() throws IOException, URISyntaxException {
    System.out.println("Initialising validator using schema: " + INVENTORY_SCHEMA_LOCATION);
    JsonSchemaValidator validator = new JsonSchemaValidator(INVENTORY_SCHEMA_LOCATION);

    System.out.println("Validating json data: " + VEHICLE_EXAMPLE);
    validator.validate(getJsonObject(VEHICLE_EXAMPLE));
  }

  @Test(expected = ValidationException.class)
  public void validationFailsForEquipementWithVehicle() throws IOException, URISyntaxException {
    System.out.println("Initialising validator using schema: " + VEHICLE_SCHEMA_LOCATION);
    JsonSchemaValidator validator = new JsonSchemaValidator(VEHICLE_SCHEMA_LOCATION);

    String jsonLocation = EXAMPLES_PATH + "/equipment.json";
    System.out.println("Validating json data: " + jsonLocation);
    validator.validate(getJsonObject(jsonLocation));
  }

}
