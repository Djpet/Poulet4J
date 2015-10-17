# Poulet4J : Code of war 2015

SDK Java (1.5 et +) pour le code of war 2015 

## Prérequis
Un IDE Java et Maven 3 (projet démo prévu pour eclipse)

## Première installation dans eclipse
Télécharger la démo [ici](http://jonathan.peturaud.free.fr/poulet4j/Bot.zip)

Dans eclipse, *File* -> *Import*, puis *General* -> *Existing Projects in to Workspace* et enfin *Finish*

Le projet est prêt.

Pour lancer un bot, *Clic droit* -> *Run As* -> *Java Application*

## API
La javadoc de l'API est disponible [ici](http://jonathan.peturaud.free.fr/poulet4j/api/index.html)

## Mise à jour de l'api
Pour mettre à jour api, il suffit de changer la version dans le fichier pom.xml à la racine du projet.

La dernière dépendance est 

```xml
		<dependency>
			<groupId>fr.poulet4j</groupId>
			<artifactId>Poulet4j</artifactId>
			<version>1.0.5</version>
		</dependency>
```

## Sauvegarde de la partie et Visualisation
Il est possible de sauvegarder la partie en ajoutant **.saveGame()** au Runner. Cela donne :
```java
        Runner.ia(new Bot1()).saveGame().connectAndRun("localhost");
```

Les sauvegardes sont enregistrées dans le dossier **save**.
Pour les lire, il suffit de lancer la classe **GuiRun.java**, choisir un fichier et se déplacer avec le slider ou les flèches. 

** Attention ** :
 - la sauvegarde ne contient que la vision du Bot et ses actions. 
 - la sauvegarde consomme un peu de temps : 10-15 ms sur le premier tours et 1-5 ms sur les tours suivants.

 
## Changelog
  
### 1.0.5
 	- API compatible avec la version 1.0.5 du serveur
 	- Sauvegarde + Visualisation
 
