## Perguntas

Consider that we want to verify the AddressResolver#findAddressForLocation, which invokes a remote geocoding service, available in a REST interface, passing the site coordinates. Which is the service to mock?


Deverá ser feito um mock da interface ISimpleHttpClient visto que é esta que comunica com a rest API externa e retorna o resultado dos pedidos. Assim, pode-se testar o comportamento da aplicação tendo por base o suposto valor de retorno da API.
