INSERT INTO `blog`.`users`
(`id`, `email`, `name`, `password`, `username`)
VALUES(1,'taher@gmail.com','Taher','$2a$10$.jsxIYKAky.zMjsnLZRjze0Hek8h50RTvWvSUGnwBfdmC6eH5vZjW','taher');
VALUES(2,'admin@gmail.com','Admin','$2a$10$.jsxIYKAky.zMjsnLZRjze0Hek8h50RTvWvSUGnwBfdmC6eH5vZjW','admin');

INSERT INTO `blog`.`roles`
(`id`, `name`)
VALUES(1,'ROLE_USER');
VALUES(2,'ROLE_ADMIN');

INSERT INTO `blog`.`user_roles`
(`user_id`, `role_id`)
VALUES(1,1);
VALUES(2,2);



