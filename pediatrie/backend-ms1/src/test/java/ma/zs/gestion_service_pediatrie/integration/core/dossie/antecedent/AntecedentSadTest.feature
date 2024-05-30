Feature: Antecedent

  Background:
    * call read('karate-config.js')
    * call read('db_cleaner.js')
    * url antecedentUrl
    * header Content-Type = 'application/json'

    * def postBody = read('AntecedentSave.json')
    * def FindAllSchema = read('AntecedentSchema.json')
    * def uuid = function() { return '' + java.util.UUID.randomUUID(); }
    * postBody.ref = uuid()

  @duplicate
  Scenario Outline: POST Antecedent Twice with same ref - expect <responseCode> as response code
    * postBody.code = uniqueId
    * def count = db.readValue('select count(*) FROM item.antecedent')

    * path ''
    * header Authorization = 'Bearer ' + adminToken
    * request postBody
    * method POST
    * status <responseCode>
    * eval if(__num==1 && count != db.readValue('select count(*) FROM item.antecedent')) karate.fail("antecedent count values in DB are different")

    Examples:
      | responseCode |
      | 201          |
      | 226          |

  Scenario: Fail - GetByID Not Found

    * path 'id', 99999999
    * header Authorization = 'Bearer ' + adminToken
    * method GET
    * status 404
    * match response.length == 0



  Scenario: Fail - POST Antecedent without Body

    * path ''
    * header Authorization = 'Bearer ' + adminToken
    * method POST
    * status 400
    * match response.error == "Bad Request"



  Scenario: Fail - POST Antecedent without Authorization

    * path ''
    * header Authorization = 'Bearer unvalid'
    * request postBody
    * method POST
    * status 500



  Scenario: Fail - Save Antecedent with method PATCH

    * path ''
    * header Authorization = 'Bearer ' + adminToken
    * method PATCH
    * status 405
    * match response.error == "Method Not Allowed"
