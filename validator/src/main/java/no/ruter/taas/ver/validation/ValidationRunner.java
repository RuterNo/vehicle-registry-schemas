package no.ruter.taas.ver.validation;


import static java.util.logging.Logger.getLogger;
import static no.ruter.taas.ver.validation.JsonSchemaValidator.detectJsonSchemaType;
import static no.ruter.taas.ver.validation.JsonSchemaValidator.getJsonObject;

import java.io.IOException;
import java.util.logging.Logger;

public class ValidationRunner {

  private static final Logger log = getLogger("ValidationRunner");

  public static void main(String[] args) throws IOException {
    String jsonLocation, schemaLocation;

    System.out.println("Working Directory = " +
        System.getProperty("user.dir"));
    ValidationRunner runner = new ValidationRunner();

    if (args.length == 1) {
      jsonLocation = args[0];
      schemaLocation = detectJsonSchemaType(jsonLocation);

    } else if (args.length == 2) {
      jsonLocation = args[0];
      schemaLocation = args[1];

    } else {
      jsonLocation = "../examples/vehicle.json";
      schemaLocation = DefaultValidationConfig.VEHICLE_SCHEMA_LOCATION;
    }

    log.info("Initialising validator using schema: " + schemaLocation);
    JsonSchemaValidator validator = new JsonSchemaValidator(schemaLocation);

    log.info("Validating json data: " + jsonLocation);
    validator.validate(getJsonObject(jsonLocation));
  }
}
