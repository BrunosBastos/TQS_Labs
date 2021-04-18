## Perguntas

### 1

a)
   assertThat( found ).isEqualTo(alex);
   assertThat(fromDb).isNull();
   assertThat(found).extracting(Employee::getName).containsOnly("bob");

b)

    Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
    Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
    Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
    Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
    Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
    Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());


c)
    MockBean faz parte do Spring Boot e permite adicionar mocks do Mockito na ApplicationContext.
    Se existir um Bean compativel com essa classe, ela é substituida pelo mock.

    Mock da libraria Mockito permite a criação de uma interface onde é possível imitar o comportamento de um objeto não sendo necessária a implementação do mesmo.

d)
    O ficheiro "application-integrationtest.properties" serve para estabelecer uma ligação à base de dados que vai ser usada nos testes de integração.
