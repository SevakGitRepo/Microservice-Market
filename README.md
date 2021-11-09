WEB application

port->8081
        ->/users
                  -> 
                     /login
                     /update
                     /delete
                     /allUser
                     /registry

1. registration user in db

2. only admin can see all user and do any change

3. if db history don't work request db don't work


*************************************************
For token
ort->8083
            ->/auth
                ->/login
                        {
                            "email": "admin@admin.com",
                            "password": "admin"
                        }
                ->/logout
                    


*************************************************
return all shopping user witch email={email}
8082->
        /shopping/{email}



AdminPartProductRestController security chka
port->8082
                ->/product
                ->/all
                /id
                /admin/add               ete id n ka qanak@ avelacnum e - ov pakasecnum
                        {       
                            "id": 2,
                            "productName": "Product2",
                            "productCount": 50,         
                            "productPrice": 200
                        }

                        /admin/delete            id ov jnjum e lriv

                        /buy
product ---- port8082 /product

security ---- port8080 /user  -----/all
                                   /id
                                   /buy

Buy um gni mas@ der grats chi bay@ kmna gin@ kpoxvi urish tex---->ANAVART


{
"productMap" : {
"1" : 30,
"2" : 30,
"3" : 30,
"4" : 30
},

    "email": "admin@admin.com"
}


***********************************************
server port 9999 -> /market -> /buy
{
"4": 1,
"5": 2  
}


server port 9999 -> /products ->
/all
/id
return all products or current product
if DB work else return standard response
                                   
