MATCH (naturaltransposableelement :NaturalTransposableElement),
(naturaltransposableelement)-[:transposableElements]-(naturaltransposableelement_transposableelements :TransposableElement),
(naturaltransposableelement_transposableelements)-[:chromosome]-(naturaltransposableelement_transposableelements_chromosome :Chromosome)
WHERE ( ANY (key in keys(naturaltransposableelement) WHERE naturaltransposableelement[key]='FBte0000007') AND (naturaltransposableelement)-[]-(Organism { shortName: 'D. melanogaster' } ))
RETURN naturaltransposableelement.primaryIdentifier,
naturaltransposableelement.symbol,
naturaltransposableelement_transposableelements.primaryIdentifier,
naturaltransposableelement_transposableelements.symbol,
naturaltransposableelement_transposableelements_chromosome.primaryIdentifier,
naturaltransposableelement_transposableelements_chromosomelocation.start,
naturaltransposableelement_transposableelements_chromosomelocation.end,
naturaltransposableelement_transposableelements_chromosomelocation.strand
ORDER BY naturaltransposableelement.primaryIdentifier ASC
