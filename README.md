# Protein-Sequence-Parser
Parser has to convert a protein sequence of various lenght and consisting of 21 kind of characters (amino-acids) into a vector of frequencies where each character has frequencies attached to it on it's position in the sequence string.

Secondary structure, occurs when the sequence of amino acids are linked by hydrogen bonds, which are responsible for protein bending, and forming shapes such as alpha-helixes and beta-sheets. These shapes form the secondary protein structures. This structure is dependant on order of residues in the protein. Ternary structure is the further order of the protein, depending on it, secondary form forms a globule or general view of the protein molecule. 

In order to predict the classify structure of the protein, we would have to  use it’s sequence. 20 standard  amino acids may be encountered in a protein sequence. Structure of protein in this form is not the most efficient, and would not suit for our neural network inputs. The input at each residue has to be coded as a letter out of an alphabet of 25. Beside the 20 standard amino acids, B (aspartic acid or asparagine), U (selenocysteine), X (unknown), Z (glutamic acid or glutamine) and . (gap) are considered. The input presented to the networks is the frequency of each of the 24 non-gap symbols, plus the total frequency of gaps in each column.[2] 

In order to make this data useful, we encode residues. We could use 25 binary inputs. i.e. : 
a 1000000000000000000000000 Alanine 
B 0100000000000000000000000 Asparagine 
c 0010000000000000000000000 Cysteine  
… ……………………………………. … 
z 0000000000000000000000010 Glutamine 
 
Network would then simultaniously take an input of the encoded sequence, with input set shifting. If we've got a sequence that is 40 characters(residues) long, we would need to process 40*25 = 1000 inputs. 



Extra information that could be retrieved from protein sequences? *Additional*
