# Global-Positionning-System
Le projet Global-Positionning-System met en place les notions vues au sein de l'UE "Génie Logiciel" 2022. Ce projet a été réalisé par 3 étudiants en L2 informatique a CYU Cergy Paris Université : Alex VOLQUARDSEN, Maxime ANDRO et Matthieu VAYSSE.
## Introduction
Ce logiciel a pour but de similuer un indicateur d'itinéraire multimodale exploitant un réseau de transport prédéfini.
## Installation
> Cette application requiert [Java 8](https://www.oracle.com/fr/java/technologies/javase/javase8-archive-downloads.html) ou une version supérieur.

Vous pouvez retrouver le dossier du projet contenant le code source [ici](https://github.com/FalTeaK/Global-Positionning-System/releases/tag/1.0).

Afin d'éxécuter le code source, la librairie [log4j](https://logging.apache.org/log4j/2.x/download.html) doit etre installée.
## Fonctionnalitées
### Création d'une nouvelle carte
Il est possible d'intégrer sa propore carte au logiciel dans `/src/engine.config` afin d'étudier les itinéraires les plus intéréssants en fonction de sa localisation. Cette carte doit respectée certaines règles afin que le programme la traduise corectement :
- Dimension : 256 x 144 pixels
- le code couleur des différents éléments est présent dans la légende, située en haut a droite de de la fenetre d'application.

**NOTE** : si la carte intégrer dans le code source est remplacée par une nouvelle carte, il va de soit que les points d'intérets contenu dans les fichiers d'extension ".csv" situés dans `/src/engine.config` ne font plus sens et doivents etre modifié ou supprimé pour convenir a la nouvelle carte.
### Ajout de points d'intérêts
Il est possible de rajouter, modifier ou supprimer des points d'intérêts danns les différents fichiers d'extension ".csv" présents dans `/csv/engine.config`. Pour ajouter un point d'intérêt, veillez a bien respecter la syntaxe des autres points d'intérêts: NomDuLieu;PositionX;PositionY. 
