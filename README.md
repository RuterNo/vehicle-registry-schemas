# Vehicle Equipment Registry json schemas

Json definition schema *drafts* for data models of the Vehicle &amp; Equipment Registry

The schemas provided in this repository define Vehicle, Equipment and Inventory data models.


### API endpoints:

 * _/vehicles_ - (Opt. filter by: __after__ _ISO8601-date-time_)
   * Returns an object like _examples/vehicle_list.json_
 * _/vehicles/{VIN}_
   * Returns an object like _examples/vehicle.json_
 * _/vehicles/{VIN}/inventory_ - (Opt. filter by: __status__, __type__)
   * Returns an object like _examples/inventory.json_
 * _/inventory_ - (Opt. filter by: __status__)
   * Returns a list of all known modules and submodules
 * _/inventory/{SerialNumber}_
   * Returns an object like _examples/equipment.json_

### Changes
The schemas are subject to change.

Fields may be added or removed, decided by Ruter and the Operators's needs.

#### Elements not yet finalized:

##### Vehicle schema
  * Prefix
  * Description
  * Type
  * TransportType
  * TypeOfBus
