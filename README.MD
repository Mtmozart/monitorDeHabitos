Aqui está uma documentação detalhada que explica a abordagem e o propósito de cada classe na estrutura de progresso por
hábito usando semanas:

---

## Documentação: Estrutura de Progresso de Hábito

### Visão Geral

O objetivo desta estrutura é fornecer uma maneira eficiente de monitorar o progresso de um hábito ao longo do tempo,
permitindo uma organização modular e uma visão clara da evolução semanal. Utilizando `WeekProgress` para acompanhar o
progresso semanal e `Progress` para o progresso total, a estrutura é escalável e facilita a análise do desempenho do
usuário.

---

### Classes Principais

1. **Classe `Habit`**
    - **Descrição**: Representa um hábito específico que o usuário deseja monitorar (por exemplo, “Correr 1 km”).
    - **Atributos**:
        - `name`: Nome do hábito.
        - `totalProgress`: Instância de `Progress` que indica o progresso acumulado do hábito.
        - `weeks`: Lista de instâncias `WeekProgress` para acompanhar o progresso semanal.
    - **Métodos**:
        - `updateTotalProgress()`: Atualiza o progresso total do hábito somando os percentuais de cada semana.
    - **Propósito**: Centralizar o acompanhamento do hábito, calculando o progresso total a partir das semanas e
      oferecendo um ponto de visualização para o progresso semanal.

2. **Classe `Progress`**
    - **Descrição**: Representa o progresso total de um hábito, usando um único valor percentual.
    - **Atributos**:
        - `percentage`: Um valor em ponto flutuante representando o percentual total de progresso (0 a 100%).
    - **Métodos**:
        - `getPercentage()`: Retorna o progresso total.
        - `setPercentage(double)`: Define o progresso total, geralmente baseado na soma dos percentuais semanais.
    - **Propósito**: Atuar como um indicador consolidado do progresso total do hábito, atualizado semanalmente.

3. **Classe `WeekProgress`**
    - **Descrição**: Representa o progresso semanal de um hábito, registrando os dias concluídos e calculando o
      percentual de acordo com o total de dias da semana.
    - **Atributos**:
        - `startDate`: Data de início da semana.
        - `endDate`: Data de término da semana.
        - `daysCompleted`: Número de dias em que o hábito foi completado na semana.
        - `totalDays`: Total de dias previstos para o hábito na semana (por exemplo, 7 dias ou dias específicos).
        - `percentage`: Percentual de progresso da semana com base nos dias completados.
    - **Métodos**:
        - `completeDay()`: Incrementa `daysCompleted` e recalcula o `percentage` para a semana.
        - `getPercentage()`: Retorna o percentual de progresso para a semana.
    - **Propósito**: Fornecer uma visão detalhada do progresso do hábito para cada semana, facilitando a análise de
      padrões semanais e atualização do progresso total.

---

### Benefícios da Abordagem

1. **Organização Modular**:
    - Ao usar `WeekProgress` para dividir o hábito em semanas, a estrutura do código se torna mais limpa, com um
      agrupamento claro que facilita a organização e o entendimento do progresso do usuário.

2. **Facilidade de Cálculo e Atualização**:
    - A cada semana, basta atualizar o progresso semanal e, em seguida, somá-lo ao `Progress` total. Isso simplifica o
      cálculo, evitando o processamento de uma lista de dias.

3. **Escalabilidade e Flexibilidade**:
    - A separação por semanas permite a expansão para outros períodos de tempo, como meses ou até mesmo divisões dentro
      da semana. Adicionar metas adicionais (por exemplo, um prêmio semanal por consistência) é mais fácil.

4. **Simplicidade e Redução de Erros**:
    - Com semanas predefinidas, a lógica de cálculo se torna menos propensa a erros e não exige regras complicadas para
      diferentes quantidades de dias em `Progress`.

5. **Visualização e Análise**:
    - A divisão semanal permite ao usuário final monitorar seu progresso em intervalos de tempo compreensíveis e
      comparar semanas facilmente, incentivando uma análise mais intuitiva do progresso.

---

### Conclusão

Esta estrutura permite uma implementação modular e fácil de entender para o monitoramento de hábitos. A divisão semanal
permite uma atualização simplificada e uma análise clara, tornando o sistema eficiente e escalável.