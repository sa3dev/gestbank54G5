INSERT INTO utilisateur(UTILISATEUR_TYPE, pseudo,  mdp) VALUES ('Administrateur','admin','admin');
----
INSERT INTO utilisateur(UTILISATEUR_TYPE, nom, prenom, pseudo, mdp, email, adresse, codePostal, ville, telephone, dateNaissance, mle) VALUES ('Conseiller', 'Michel', 'Francoise', 'FMichel', 'FMichel', 'fmichel@gest-bank.ky', "99, avenue du Marechal Juin","06700","SAINT-LAURENT-DU-VAR","0108782768","2015-12-16", 1);
INSERT INTO utilisateur(UTILISATEUR_TYPE, nom, prenom, pseudo, mdp, email, adresse, codePostal, ville, telephone, dateNaissance, mle) VALUES ('Conseiller', 'Rober', 'Frank', 'FRober', 'FRober', 'frober@gest-bank.ky', "26, rue de Groussay","93230","ROMAINVILLE","0229276644","2017-04-08", 2);
INSERT INTO utilisateur(UTILISATEUR_TYPE, nom, prenom, pseudo, mdp, email, adresse, codePostal, ville, telephone, dateNaissance, mle) VALUES ('Conseiller', 'Durant', 'Jacques', 'JDurant', 'JDurant', 'jdurant@gest-bank.ky', "84, boulevard Amiral Courbet","69600","OULLINS","0168989836","2017-04-28", 3);
----
INSERT INTO utilisateur(UTILISATEUR_TYPE, nom, prenom, pseudo, mdp, email, adresse, codePostal, ville, telephone, dateNaissance, identifiant, revenuMens, conseiller_pseudo) VALUES ('Client', 'Davis', 'Mark', 'MDavis', 'MDavis', 'mark.davis@b-mail.ky',"51 Rue du Palais","93800","ÉPINAY-SUR-SEINE","0970367846","2017-09-06", 1, 2900, 'FMichel');
INSERT INTO utilisateur(UTILISATEUR_TYPE, nom, prenom, pseudo, mdp, email, adresse, codePostal, ville, telephone, dateNaissance, identifiant, revenuMens, conseiller_pseudo) VALUES ('Client', 'Davis', 'Helen', 'HDavis', 'HDavis', 'helen.davis@b-mail.ky', "2, rue Lenotre","78120","RAMBOUILLET","0616853967","2015-11-17", 2, 2650, 'FRober');
INSERT INTO utilisateur(UTILISATEUR_TYPE, nom, prenom, pseudo, mdp, email, adresse, codePostal, ville, telephone, dateNaissance, identifiant, revenuMens, conseiller_pseudo) VALUES ('Client', 'Damon', 'Barn', 'BDamon', 'BDamon', 'barn.damon@b-mail.ky', "40, Chemin des Bateliers","73100","AIX-LES-BAINS","0150708652","2015-10-27", 3, 2500, 'FRober');
INSERT INTO utilisateur(UTILISATEUR_TYPE, nom, prenom, pseudo, mdp, email, adresse, codePostal, ville, telephone, dateNaissance, identifiant, revenuMens, conseiller_pseudo) VALUES ('Client', 'Thomas', 'William', 'WThomas', 'WThomas', 'will.thoms@a-mail.ky',"61, rue des Dunes","35400","SAINT-MALO","0628514439","2016-08-17", 4, 2600, 'JDurant');
INSERT INTO utilisateur(UTILISATEUR_TYPE, nom, prenom, pseudo, mdp, email, adresse, codePostal, ville, telephone, dateNaissance, identifiant, revenuMens, conseiller_pseudo) VALUES ('Client', 'Wong', 'Mickael', 'MWong', 'MWong', 'mickael.wong2@c-mail.ky',"67, Avenue Millies Lacroix","59640","DUNKERQUE","0982163213","2016-12-06", 5, 3800, 'JDurant');
INSERT INTO utilisateur(UTILISATEUR_TYPE, nom, prenom, pseudo, mdp, email, adresse, codePostal, ville, telephone, dateNaissance, identifiant, revenuMens, conseiller_pseudo) VALUES ('Client', 'Davis', 'Chris', 'CDavis', 'CDavis', 'chris.davis@y-mail.ky', "23, place Stanislas","54100","NANCY","0169369580","2017-05-19", 6, 4100, 'FMichel');
INSERT INTO utilisateur(UTILISATEUR_TYPE, nom, prenom, pseudo, mdp, email, adresse, codePostal, ville, telephone, dateNaissance, identifiant, revenuMens, conseiller_pseudo) VALUES ('Client', 'Gibbs', 'Anthony', 'AGibbs', 'AGibbs', 'antonio.gibbs@n-mail.ky',"42, Rue de Verdun","71300","MONTCEAU-LES-MINES","0604243751","2017-06-26", 7, 5000, 'JDurant');

INSERT INTO clientpotentiel (nom, prenom, pseudo, email, adresse, codePostal, ville, telephone, dateNaissance, revenuMens) VALUES ("Audibert", "Julien", "JAudibert", "julien.audibert@gmail.com", "47, route de Lyon", "13800", "ISTRES", "0144753957","2016-12-18",3020 );
INSERT INTO clientpotentiel (nom, prenom, pseudo, email, adresse, codePostal, ville, telephone, dateNaissance, revenuMens) VALUES ("Pouliotte", "Olivier", "OPouliotte", "olivier.audibert@gmail.com", "35, rue du Faubourg National", "33400", "TALENCE", "0622302733","2017-08-14",2450);
INSERT INTO clientpotentiel (nom, prenom, pseudo, email, adresse, codePostal, ville, telephone, dateNaissance, revenuMens) VALUES ("Boucher", "Gaetan", "GBoucher", "gaetan.boucher@gmail.com", "59, rue Victor Hugo", "60200", "COMPIÈGNE", "0730893473","2016-10-29",3430);
INSERT INTO clientpotentiel (nom, prenom, pseudo, email, adresse, codePostal, ville, telephone, dateNaissance, revenuMens) VALUES ("Lavallee", "Estelle", "ELavallee", "gaetan.boucher@gmail.com", "82, rue Banaudon", "69006", "LYON", "0891300964","2015-11-16",5270);

INSERT INTO demandeouverture (numDemande, dateAffectation, dateCreation, cp_pseudo, status) VALUES ('1', NULL, '2016-10-02', 'JAudibert', false);
INSERT INTO demandeouverture (numDemande, dateAffectation, dateCreation, cp_pseudo, status) VALUES ('2', NULL, '2016-10-08', 'OPouliotte', false);
INSERT INTO demandeouverture (numDemande, dateAffectation, dateCreation, cp_pseudo, status) VALUES ('3', NULL, '2016-10-14', 'GBoucher', false);
INSERT INTO demandeouverture (numDemande, dateAffectation, dateCreation, cp_pseudo, status) VALUES ('4', NULL, '2016-10-22', 'ELavallee', false);

INSERT INTO compte (noCompte, RIB, decouvert, montantAgios, montanRemuneration, seuilRemuneration, solde) VALUES (2354789, 0, 0, 0, 0, 0, 500);
INSERT INTO compte (noCompte, RIB, decouvert, montantAgios, montanRemuneration, seuilRemuneration, solde) VALUES (2354790, 0, 0, 0, 0, 0, 2000);
INSERT INTO compte (noCompte, RIB, decouvert, montantAgios, montanRemuneration, seuilRemuneration, solde) VALUES (2354791, 0, 0, 0, 0, 0, 3500);
INSERT INTO compte (noCompte, RIB, decouvert, montantAgios, montanRemuneration, seuilRemuneration, solde) VALUES (2354792, 0, 0, 0, 0, 0, 18000);
INSERT INTO compte (noCompte, RIB, decouvert, montantAgios, montanRemuneration, seuilRemuneration, solde) VALUES (2354793, 0, 0, 0, 0, 15000, 32000);
INSERT INTO compte (noCompte, RIB, decouvert, montantAgios, montanRemuneration, seuilRemuneration, solde) VALUES (2354794, 0, 0, 0, 0, 0, 300);
INSERT INTO compte (noCompte, RIB, decouvert, montantAgios, montanRemuneration, seuilRemuneration, solde) VALUES (2354795, 0, 0, 0, 0, 15000, 18000);
INSERT INTO compte (noCompte, RIB, decouvert, montantAgios, montanRemuneration, seuilRemuneration, solde) VALUES (2354796, 0, 0, 0, 0, 0, 8000);
INSERT INTO compte (noCompte, RIB, decouvert, montantAgios, montanRemuneration, seuilRemuneration, solde) VALUES (2354797, 0, 0, 0, 0, 0, 6000);
INSERT INTO compte (noCompte, RIB, decouvert, montantAgios, montanRemuneration, seuilRemuneration, solde) VALUES (2354798, 0, 0, 0, 0, 0, 7500);
INSERT INTO compte (noCompte, RIB, decouvert, montantAgios, montanRemuneration, seuilRemuneration, solde) VALUES (2354799, 0, 0, 0, 0, 15000, 18000);

INSERT INTO utilisateur_compte (utilisateur_pseudo, listeComptes_noCompte) VALUES ('MDavis', 2354789);
INSERT INTO utilisateur_compte (utilisateur_pseudo, listeComptes_noCompte) VALUES ('HDavis', 2354790);
INSERT INTO utilisateur_compte (utilisateur_pseudo, listeComptes_noCompte) VALUES ('BDamon', 2354791);
INSERT INTO utilisateur_compte (utilisateur_pseudo, listeComptes_noCompte) VALUES ('WThomas', 2354792);
INSERT INTO utilisateur_compte (utilisateur_pseudo, listeComptes_noCompte) VALUES ('MWong', 2354793);
INSERT INTO utilisateur_compte (utilisateur_pseudo, listeComptes_noCompte) VALUES ('CDavis', 2354794);
INSERT INTO utilisateur_compte (utilisateur_pseudo, listeComptes_noCompte) VALUES ('AGibbs', 2354795);
INSERT INTO utilisateur_compte (utilisateur_pseudo, listeComptes_noCompte) VALUES ('MDavis', 2354796);
INSERT INTO utilisateur_compte (utilisateur_pseudo, listeComptes_noCompte) VALUES ('HDavis', 2354797);
INSERT INTO utilisateur_compte (utilisateur_pseudo, listeComptes_noCompte) VALUES ('BDamon', 2354798);
INSERT INTO utilisateur_compte (utilisateur_pseudo, listeComptes_noCompte) VALUES ('MDavis', 2354799);

INSERT INTO transaction (noTransaction, dateTransaction, libelle, montant, typeTransaction) VALUES ('1', '2016-10-02', "credit de 2650", '2650','credit');
INSERT INTO transaction (noTransaction, dateTransaction, libelle, montant, typeTransaction) VALUES ('2', '2016-10-04', "debit de 40", '40','debit');
INSERT INTO transaction (noTransaction, dateTransaction, libelle, montant, typeTransaction) VALUES ('3', '2016-10-05', "debit de 20", '20','debit');
INSERT INTO transaction (noTransaction, dateTransaction, libelle, montant, typeTransaction) VALUES ('4', '2016-10-09', "debit de 220", '220','debit');
INSERT INTO transaction (noTransaction, dateTransaction, libelle, montant, typeTransaction) VALUES ('5', '2016-10-10', "debit de 1220", '1220','debit');
INSERT INTO transaction (noTransaction, dateTransaction, libelle, montant, typeTransaction) VALUES ('6', '2016-10-10', "credit de 1220", '1220','credit');
INSERT INTO transaction (noTransaction, dateTransaction, libelle, montant, typeTransaction) VALUES ('7', '2016-10-12', "debit de 550", '550','debit');

INSERT INTO compte_transaction (compte_noCompte, listeTransactions_noTransaction) VALUES ('2354790', '1');
INSERT INTO compte_transaction (compte_noCompte, listeTransactions_noTransaction) VALUES ('2354790', '3');
INSERT INTO compte_transaction (compte_noCompte, listeTransactions_noTransaction) VALUES ('2354790', '4');
INSERT INTO compte_transaction (compte_noCompte, listeTransactions_noTransaction) VALUES ('2354790', '5');
INSERT INTO compte_transaction (compte_noCompte, listeTransactions_noTransaction) VALUES ('2354797', '2');
INSERT INTO compte_transaction (compte_noCompte, listeTransactions_noTransaction) VALUES ('2354797', '6');
INSERT INTO compte_transaction (compte_noCompte, listeTransactions_noTransaction) VALUES ('2354797', '7');

