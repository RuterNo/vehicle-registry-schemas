# Vehicle Equipment Registry json schemas

Json definition schema *drafts* for data models of the Vehicle &amp; Equipment Registry

The schemas provided in this repository define Vehicle, Equipment and Inventory data models.


### API endpoints:

 * _/vehicles_ - (Opt. filter by: __after__ _ISO8601-datetime_)
   * Returns an object like _examples/vehicle_list.json_
   * _/vehicles?after={datetime}_
 * _/vehicles/{VIN}_
   * Returns an object like _examples/vehicle.json_
 * _/vehicles/{VIN}/inventory_ - (Opt. filter by: __status__, __type__)
   * Returns an object like _examples/inventory.json_
   * _/vehicles/{VIN}/inventory?status={code}_
   * _/vehicles/{VIN}/inventory?type={type}_
   * _/vehicles/{VIN}/inventory?status={code}&type={type}_
 * _/inventory_ - (Opt. filter by: __status__, __type__)
   * Returns an object like _examples/inventory_list.json_
   * _/inventory?status={code}_
   * _/inventory?type={type}_
   * _/inventory?status={code}&type={type}_
 * _/inventory/{SerialNumber}_
   * Returns an object like _examples/equipment.json_

### Changes
The schemas are subject to change.

Fields may be added or removed, decided by Ruter and the Operators's needs.
