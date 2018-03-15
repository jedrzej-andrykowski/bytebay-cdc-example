import org.springframework.cloud.contract.spec.Contract

[

        Contract.make {
            request {
                name("create a user")
                method 'POST'
                urlPath('/users')
                headers {
                    header 'Content-Type': 'application/json'
                }
                body(
                        name: $(client(regex('[a-zA-Z]+')), server('Jan')),
                )
            }

            response {
                status 201
                body(
                        id: $(client('3f19ee87-a3ce-4c78-a4da-c7b977eee219'), server(regex('[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}'))),
                        name: $(client('Jan'), server(regex('[a-zA-Z]+')))
                )
                headers {
                    header 'Content-Type': 'application/json;charset=UTF-8'
                }
            }
        }
]
