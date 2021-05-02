## TQS Lab6

## Bruno de Sousa Bastos NMEC:93302


1

f)

    Bug:
        "Random objects should be used"
        
        ex:
        ```
            Random generator = new Random();    
        ```

        Para dar fix pode-se instanciar o objeto Random apenas uma vez e depois usa-lo para gerar valores aleatorios.


    Nao Existem vulnerabilidades.

    Code Smell:

        "Do not assign loop counter from withing the loop body"

        ex:
        ```
            for (int i = 0; i < NUMBERS_LEN; ) {
                int candidate = generator.nextInt(NUMBERS_BOUND) + 1;
                if (!randomDip.getNumbersColl().contains(candidate)) {
                    randomDip.getNumbersColl().add(candidate);
                    i++;
                }
            }
        ```

        Solução é incrementar a variavel i dentro do for. for(int i=0; i< NUMBERS_LEN; i++ )


        "Replace System.out and System.err by a logger"

        ex:
            ```
            System.out.println("Betting with three random bets...");
            ```

        Utilizar um logger para quando se quer mostrar algo no ecrã.


2
a)

    Code Smells: 27
    Debt: 2h13min
    Debt Ratio: 1.6%
    Rating: A
    Effort to reach A: 0


    Debt é uma estimativa do esforço necessário para aperfeiçoar o codigo.
    Debt Ratio é a percentagem de codigo que necessita de ser alterado.

b) 
    Maior parte dos major code smells era devido a utilização de System.out em vez de loggers.

        
    
d)
    44 linhas de codigo ainda por cobrir, sendo que 67.4% das linhas ja tem cobertura.
    13 condições para cobrir, sendo que 76.8% das condiçoes ja estão cobertas.
    70.2% de coverage total do projeto.
