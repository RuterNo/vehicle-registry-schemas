package no.ruter.taas.ver.validation;

class DefaultValidationConfig {

  private static String SCHEMA_PATH = "../schemas/";
  static String EXAMPLES_PATH = "../examples";

  static String VEHICLE_SCHEMA = "vehicle.schema.json";
  static String EQUIPMENT_SCHEMA = "equipment.schema.json";
  static String INVENTORY_SCHEMA = "inventory.schema.json";
  static String VEHICLE_LIST_SCHEMA = "vehicle_list.schema.json";
  static String VEHICLE_SCHEMA_LOCATION = SCHEMA_PATH + VEHICLE_SCHEMA;
  static String EQUIPMENT_SCHEMA_LOCATION = SCHEMA_PATH + EQUIPMENT_SCHEMA;
  static String INVENTORY_SCHEMA_LOCATION = SCHEMA_PATH + INVENTORY_SCHEMA;
  static String VEHICLE_LIST_SCHEMA_LOCATION = SCHEMA_PATH + VEHICLE_LIST_SCHEMA;

  static String VEHICLE_EXAMPLE = EXAMPLES_PATH + "/vehicle.json";

}
