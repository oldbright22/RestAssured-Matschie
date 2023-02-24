Feature: Create New Incident

Scenario Outline: Create new incident with short description

Given enable logs
And short description added with AddedFromCucumber
And new incident created
When the status code is 201
And response includes the following
#Map => Key, Value
#      |Key|Value| 
 |result.sys_created_by|admin|
 |result.sys_class_name|incident|