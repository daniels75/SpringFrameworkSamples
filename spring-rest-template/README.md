POST http://localhost:8093/cars/5/fiat
GET  http://localhost:8093/cars/5

POST http://localhost:8093/complex/1
    {
        "age" : 10,
        "name": "Audi"
    }
GET  http://localhost:8093/complex/1


Rest Template example
http://localhost:8093/complex/template/1
{
	"age" : 10,
	"name": "Audi"
}

GET http://localhost:8093/complex/map/1
POST http://localhost:8093/complex/map/1
{
    "12345": {
        "age": 10,
        "name": "simpleCar"
    }
}

POST http://localhost:8093/complex/map/template/1
-- no body

complex String -> String,Map
{
    "12345": {
        "age": 10,
        "name": "simpleCar"
    }
}
