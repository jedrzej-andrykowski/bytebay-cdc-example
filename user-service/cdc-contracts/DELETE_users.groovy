import org.springframework.cloud.contract.spec.Contract

[
        Contract.make {
            request {
                name("delete a user")
                method 'DELETE'
                urlPath('/users/3f19ee87-a3ce-4c78-a4da-c7b977eee219')
            }

            response {
                status 204
            }
        }
]
