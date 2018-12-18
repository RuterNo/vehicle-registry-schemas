package no.ruter.taas.ver.validation;

class DefaultValidationConfig {

  private static String SCHEMA_PATH = "../schemas";
  private static String EXAMPLES_PATH = "../examples";
  static String RESOLUTION_SCOPE = "https://raw.githubusercontent.com/RuterNo/ver-schemas/master/schemas/";

  static String VEHICLE_SCHEMA_LOCATION = SCHEMA_PATH + "/vehicle.schema.json";
  static String EQUIPMENT_SCHEMA_LOCATION = SCHEMA_PATH + "/equipment.schema.json";
  static String INVENTORY_SCHEMA_LOCATION = SCHEMA_PATH + "/inventory.schema.json";
  static String VEHICLE_LIST_SCHEMA_LOCATION = SCHEMA_PATH + "/vehicle_list.schema.json";

  static String VEHICLE_EXAMPLE = EXAMPLES_PATH + "/vehicle.json";

}
