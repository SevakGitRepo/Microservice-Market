For token

port->8083
            ->/auth
                    ->/login
                                {
                                    "email": email,
                                    "password": password,
                                }
                                    return token
                    ->/logout

SecurityApplication Bean passwordencoder
