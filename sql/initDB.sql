USE Clinique_veto;  
GO  

ALTER TABLE dbo.Agendas
DROP CONSTRAINT FK_Agendas;  
GO  

ALTER TABLE dbo.Agendas
DROP CONSTRAINT FK_Agendas_Veto;  
GO  

ALTER TABLE dbo.Animaux
DROP CONSTRAINT FK_Animaux;  
GO  

ALTER TABLE dbo.Animaux
DROP CONSTRAINT FK_ANIMAUX_RACES;  
GO  


truncate table dbo.Agendas;
truncate table dbo.Races
truncate table dbo.Clients;
truncate table dbo.Animaux;
truncate table dbo.Personnels;

GO

ALTER TABLE dbo.Agendas ADD CONSTRAINT
	FK_Agendas FOREIGN KEY 	( CodeAnimal )
	references Animaux (CodeAnimal ) 
	
ALTER TABLE dbo.Agendas ADD CONSTRAINT
	FK_Agendas_Veto FOREIGN KEY 	( CodeVeto )
	references Personnels ( CodePers ) 
	
ALTER TABLE dbo.Animaux ADD CONSTRAINT
	FK_Animaux FOREIGN KEY 	( CodeClient )
	references Clients (CodeClient )

ALTER TABLE dbo.Animaux ADD CONSTRAINT
	FK_ANIMAUX_RACES FOREIGN KEY (Race,Espece)
	references Races (Race,Espece)


-- Admin
insert into Personnels values('admin', 'admin', 'ADM', 0); 

-- Races
insert into Races values('chien gentil', 'Chien');
insert into Races values('chien méchant', 'Chien');
insert into Races values('chien malin', 'Chien');
insert into Races values('chien con-con', 'Chien');
insert into Races values('chien pervers', 'Chien');
insert into Races values('chat gentil', 'Chat');
insert into Races values('chat méchant', 'Chat');
insert into Races values('cheval gentil', 'Cheval');
insert into Races values('cheval méchant', 'Cheval');
insert into Races values('crocodile gentil', 'Crocodile');
insert into Races values('crocodile méchant', 'Crocodile');
insert into Races values('vache gentile', 'Vache');
insert into Races values('vache méchante', 'Vache');