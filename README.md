# Primer-Parcial-Desarrollo-de-Software-Facundo-Ferreira

Para utilizar la API enviar una solicitud tipo POST desde Postman utilizando la siguiente URL https://primer-parcial-desarrollo-de-software-782w.onrender.com/mutant

Enviar solicitudes JSON con el siguiente formato

{
    "dna": [
    "ATGCGA",
    "CAGTGC",
    "TTATGT",
    "AGGTAT",
    "CCCTAA",
    "TCAAAA"]
}

O

{
    "dna": ["ATGCGA","CAGTGC","TTATGT","AGGTAT","CCCTAA","TCAAAA"]
}

La api deberia devolver un HTTP200 OK con el mensaje es mutante, o un HTTP403 Forbidden con el mensaje no es mutante

Aqui tiene unos ejemplos si desea testearlos
-------MUTANTES-------

{"dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]}

{"dna": ["AAAAGT", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]}

{"dna": ["ATGCGA", "CAGTAC", "GTTTTT", "AGTAGG", "CCCATA", "TCACTG"]}

-------NO MUTANTES-------

{"dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGATAC", "CCACTA", "TCACTG"]}

{"dna": ["ATGCGA", "CAGTGC", "TTGTGT", "AGATAG", "CACCTA", "TCACTG"]}

{"dna": ["ATGCGA", "CCGTGC", "TTAAGT", "AGATCT", "CACTCA", "TCACTG"]}

Ademas de estas, es mas que bienvenido a probar sus propias cadenas

Si usted desea consultar las Estadisticas debera realizar una solicitud tipo GET desde Postman utilizando la siguiente URL https://primer-parcial-desarrollo-de-software-782w.onrender.com/stats
