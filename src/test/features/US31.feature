Feature: Como oferente quiero separar mis ofertas en vencidas y no vencidas

Scenario: Un oferente ve una jobOffer no vencida

	Given existe una jobOffer "Programador Java" con vencimiento 1 dia despues de hoy
	When el oferente va a ver las jobOffers no vencidas
	Then el oferente ve la jobOffer "Programador Java" 

Scenario: Un oferente ve una jobOffer vencida

	Given existe una jobOffer "Programador Java" con vencimiento ayer
	When  el oferente va a ver las jobOffers vencidas
	Then el oferente ve la jobOffer "Programador Java"
