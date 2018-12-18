package no.ruter.taas.ver.validation;


import static no.ruter.taas.ver.validation.JsonSchemaValidator.getJsonObject;

import java.io.IOException;

public class ValidationRunner {

  public static void main(String[] args) throws IOException {
    String jsonLocation, schemaLocation;

    System.out.println("Working Directory = " + System.getProperty("user.dir"));

    if (args.length == 2) {
      jsonLocation = args[0];
      schemaLocation = args[1];

    } else {
      jsonLocation = "examples/vehicle.json";
      schemaLocation = "schemas/vehicle.schema.json";
    }

    System.out.println("Initialising validator using schema: " + schemaLocation);
    JsonSchemaValidator validator = new JsonSchemaValidator(schemaLocation);

    System.out.println("Validating json data: " + jsonLocation);
    validator.validateAndPrintInfo(getJsonObject(jsonLocation));
  }
}
