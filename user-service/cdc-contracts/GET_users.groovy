import org.springframework.cloud.contract.spec.Contract

[
        Contract.make {
            request {
                name("get a user")
                method 'GET'
                urlPath('/users/07f9fde1-c348-4c4c-81db-92953d45745d')
            }

            response {
                status 200
                body(
                        id: $(client('07f9fde1-c348-4c4c-81db-92953d45745d'), server(regex('[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}'))),
                        name: $(client('Jan'), server(regex('[a-zA-Z]+')))
                )
                headers {
                    header 'Content-Type': 'application/json;charset=UTF-8'
                }
            }
        },
        Contract.make {
            request {
                name("user not found")
                method 'GET'
                urlPath('/users/34930004-16d3-4cc9-a514-414849535bb3')
            }

            response {
                status 404
            }
        },
        Contract.make {
            request {
                name("get all users")
                method 'GET'
                urlPath('/users')
            }

            response {
                status 200
                body([[
                        id  : $(client('3f19ee87-a3ce-4c78-a4da-c7b977eee219'), server(regex('[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}'))),
                        name: $(client('Jan'), server(regex('[a-zA-Z]+')))

                ]])
                headers {
                    header 'Content-Type': 'application/json;charset=UTF-8'
                }
            }
        }
]
