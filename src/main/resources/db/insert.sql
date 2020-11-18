SET FOREIGN_KEY_CHECKS = 0;

truncate table pet;
truncate table store;

INSERT into store(`id`, `name`, `location`, `contact_no`)
VALUES(21, 'super store', 'lagos', '09031861100');

INSERT into pet(`id`, `name`, `color`, `breed`, `age`, `pet_sex`, `store_id`)
VALUES(31, 'jill', 'blue', 'parrot', 6, 'MALE', 21),
(11, 'blue', 'white', 'dog', 2, 'MALE', 21),
(10, 'ross', 'grey', 'rabbit', 3, 'MALE', 21),
(4, 'fola', 'brown', 'pig', 6, 'MALE', 21);

SET FOREIGN_KEY_CHECKS = 1;