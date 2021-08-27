CREATE TABLE `Temas` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`categoria` varchar(255) NOT NULL,
	`descricao` varchar(255) NOT NULL,
	`seja_voluntarie` BOOLEAN NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Postagens` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`texto` varchar(5000) NOT NULL,
	`titulo` varchar(255) NOT NULL,
	`comentario` varchar(255) NOT NULL,
	`data` TIMESTAMP NOT NULL,
	`horario` TIMESTAMP NOT NULL,
	`usuario_id` bigint NOT NULL,
	`tema_id` bigint NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Usuarios` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`nome_completo` varchar(70) NOT NULL,
	`email` varchar(70) NOT NULL,
	`senha` varchar(500) NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `Postagens` ADD CONSTRAINT `Postagens_fk0` FOREIGN KEY (`usuario_id`) REFERENCES `Usuarios`(`id`);

ALTER TABLE `Postagens` ADD CONSTRAINT `Postagens_fk1` FOREIGN KEY (`tema_id`) REFERENCES `Temas`(`id`);




