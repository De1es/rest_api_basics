INSERT INTO gift_certificate(name, description, price, duration, create_date, last_update_date)
VALUES ('Gift1', 'Description1', 100, 10, '2010-01-01 11:00:00+03',
        '2010-01-01 11:00:00+03');

INSERT INTO gift_certificate(name, description, price, duration, create_date, last_update_date)
VALUES ('Gift2', 'Description2', 200, 20, '2010-01-01 11:00:00+03',
        '2010-01-01 11:00:00+03');

INSERT INTO gift_certificate(name, description, price, duration, create_date, last_update_date)
VALUES ('Gift3', 'Description3', 300, 30, '2010-01-01 11:00:00+03',
        '2010-01-01 11:00:00+03');

INSERT INTO gift_certificate(name, description, price, duration, create_date, last_update_date)
VALUES ('Gift4', 'Description4', 400, 40, '2010-01-01 11:00:00+03',
        '2010-01-01 11:00:00+03');

INSERT INTO gift_certificate(name, description, price, duration, create_date, last_update_date)
VALUES ('Gift5', 'Description5', 500, 50, '2010-01-01 11:00:00+03',
        '2010-01-01 11:00:00+03');

INSERT INTO tag(name)
VALUES ('Tag1');

INSERT INTO tag(name)
VALUES ('Tag2');

INSERT INTO tag(name)
VALUES ('Tag3');

INSERT INTO tag(name)
VALUES ('Tag4');

INSERT INTO tag(name)
VALUES ('Tag5');

INSERT INTO public.gift_certificate_tag(gift_certificate_id, tag_id)
VALUES (1, 2);

INSERT INTO public.gift_certificate_tag(gift_certificate_id, tag_id)
VALUES (1, 3);

INSERT INTO public.gift_certificate_tag(gift_certificate_id, tag_id)
VALUES (2, 1);

INSERT INTO public.gift_certificate_tag(gift_certificate_id, tag_id)
VALUES (3, 1);

INSERT INTO public.gift_certificate_tag(gift_certificate_id, tag_id)
VALUES (3, 3);

INSERT INTO public.gift_certificate_tag(gift_certificate_id, tag_id)
VALUES (3, 4);

INSERT INTO public.gift_certificate_tag(gift_certificate_id, tag_id)
VALUES (5, 2);

INSERT INTO public.gift_certificate_tag(gift_certificate_id, tag_id)
VALUES (5, 5);