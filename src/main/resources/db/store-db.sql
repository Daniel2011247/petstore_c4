SET FOREIGN_KEY_CHECKS = 0;

truncate table store;

INSERT into store(`id`, `name`, `location`, `contact_no`)
VALUES(10, 'Big man pet store', 'Enugu', '09031861100'),
      (21, 'Beta Pikin pet store', 'Abia', '09031861100');

SET FOREIGN_KEY_CHECKS = 1;