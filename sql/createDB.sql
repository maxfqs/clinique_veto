USE Clinique_veto;  
GO 

/*   Application : SQL Server Enterprise Manager*/
CREATE TABLE dbo.Personnels
	(
 	CodePers int NOT NULL IDENTITY(1,1),
	Nom		 varchar(30) NOT NULL,
	MotPasse varchar(10) NOT NULL,
	Role	 varchar(3) NOT NULL,
	Archive bit NOT NULL
	) 

ALTER TABLE dbo.Personnels ADD CONSTRAINT
	PK_Personnels PRIMARY KEY NONCLUSTERED 
	(
	CodePers
	) 
	
ALTER TABLE dbo.Personnels ADD CONSTRAINT 
	U_Nom_Personnel UNIQUE(Nom)  

/*********************************************************************************************/
/*   Application : SQL Server Enterprise Manager*/
CREATE TABLE dbo.Races
	(
 	Race varchar(20) NOT NULL,
	Espece varchar(20) NOT NULL
	) 

ALTER TABLE dbo.Races ADD CONSTRAINT
	PK_Races PRIMARY KEY NONCLUSTERED 
	(
	Race,
	Espece
	) 
	
/*********************************************************************************************/
/*   Application : SQL Server Enterprise Manager*/

CREATE TABLE dbo.Clients
	(
 	CodeClient int NOT NULL IDENTITY(1,1),
	NomClient varchar(20) NOT NULL,
	PrenomClient varchar(20) NOT NULL,
	Adresse1 varchar(30) NULL,
	Adresse2 varchar(30) NULL,
	CodePostal char(6) NULL,
	Ville varchar(25) NULL,
	NumTel varchar(15) NULL,
	Assurance varchar(30) NULL,
	Email varchar(20) NULL ,
	Remarque text NULL,
	Archive bit NOT NULL
	) 

ALTER TABLE dbo.Clients ADD CONSTRAINT
	PK_Clients PRIMARY KEY NONCLUSTERED 
	(
	codeClient
	) 	
	
/*******************************************************************************************/
/*   Application : SQL Server Enterprise Manager*/
CREATE TABLE dbo.Animaux
	(
 	CodeAnimal int NOT NULL IDENTITY(1,1),
	NomAnimal varchar(30) NOT NULL ,
	Sexe char(1) NOT NULL ,
	Couleur varchar(20) NULL,
	Race varchar(20) NOT NULL,
	Espece varchar(20) NOT NULL,
	CodeClient int NOT NULL,
	Tatouage varchar(10) NULL,
	Antecedents text NULL,
	Archive bit NOT NULL
	) 

ALTER TABLE dbo.Animaux ADD CONSTRAINT
	PK_Animaux PRIMARY KEY NONCLUSTERED 
	(
	CodeAnimal
	)

ALTER TABLE dbo.Animaux ADD CONSTRAINT
	FK_Animaux FOREIGN KEY 	( CodeClient )
	references Clients (CodeClient )

ALTER TABLE dbo.Animaux ADD CONSTRAINT
	FK_ANIMAUX_RACES FOREIGN KEY (Race,Espece)
	references Races (Race,Espece)
	
	
/**********************************************************************************************/
/*   Application : SQL Server Enterprise Manager*/

CREATE TABLE dbo.Agendas
	(
 	CodeVeto int NOT NULL,
	DateRdv smalldatetime NOT NULL,
	CodeAnimal int NOT NULL
	) 

ALTER TABLE dbo.Agendas ADD CONSTRAINT
	PK_Agendas PRIMARY KEY NONCLUSTERED 
	(
	CodeVeto,
	CodeAnimal,
	DateRdv
	)
	
ALTER TABLE dbo.Agendas ADD CONSTRAINT
	FK_Agendas FOREIGN KEY 	( CodeAnimal )
	references Animaux (CodeAnimal ) 
	
ALTER TABLE dbo.Agendas ADD CONSTRAINT
	FK_Agendas_Veto FOREIGN KEY 	( CodeVeto )
	references Personnels ( CodePers ) 