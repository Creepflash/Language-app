-- Estonian UI text for seeded lessons and exercises (English -> Estonian)

UPDATE lessons SET title = 'Õppetund 1: Tervitused', description = 'Õpi igapäevaseid tervitusi ja viisakaid väljendeid.' WHERE order_index = 1;
UPDATE lessons SET title = 'Õppetund 2: Enesetutvustus', description = 'Tutvusta end lihtsate lausetega.' WHERE order_index = 2;
UPDATE lessons SET title = 'Õppetund 3: Numbrid', description = 'Harjuta loendamist lihtsates olukordades.' WHERE order_index = 3;

UPDATE exercises SET question = 'Kuidas öeldakse „tere“? (mari keeles)' WHERE lesson_id = (SELECT id FROM lessons WHERE order_index = 1) AND order_index = 1;
UPDATE exercises SET question = 'Kirjuta tänusõna (mari keeles).' WHERE lesson_id = (SELECT id FROM lessons WHERE order_index = 1) AND order_index = 2;
UPDATE exercises SET question = 'Vali väljend „head hommikust“ (mari keeles).' WHERE lesson_id = (SELECT id FROM lessons WHERE order_index = 1) AND order_index = 3;
UPDATE exercises SET question = 'Kirjuta hüvastijätusõna (mari keeles).' WHERE lesson_id = (SELECT id FROM lessons WHERE order_index = 1) AND order_index = 4;
UPDATE exercises SET question = 'Milline variant tähendab „palun“?' WHERE lesson_id = (SELECT id FROM lessons WHERE order_index = 1) AND order_index = 5;

UPDATE exercises SET question = 'Kirjuta „Minu nimi on …“ (mari tekstina).' WHERE lesson_id = (SELECT id FROM lessons WHERE order_index = 2) AND order_index = 1;
UPDATE exercises SET question = 'Vali väljend „Ma olen Eestist“ (mari keeles).' WHERE lesson_id = (SELECT id FROM lessons WHERE order_index = 2) AND order_index = 2;
UPDATE exercises SET question = 'Kirjuta „Olen üliõpilane“ (mari keeles).' WHERE lesson_id = (SELECT id FROM lessons WHERE order_index = 2) AND order_index = 3;
UPDATE exercises SET question = 'Kuidas küsida „Mis su nimi on?“ (mari keeles)' WHERE lesson_id = (SELECT id FROM lessons WHERE order_index = 2) AND order_index = 4;
UPDATE exercises SET question = 'Kirjuta „Meeldiv kohtuda“ (mari keeles).' WHERE lesson_id = (SELECT id FROM lessons WHERE order_index = 2) AND order_index = 5;

UPDATE exercises SET question = 'Vali number „üks“ (mari keeles).' WHERE lesson_id = (SELECT id FROM lessons WHERE order_index = 3) AND order_index = 1;
UPDATE exercises SET question = 'Kirjuta number „kaks“ (mari keeles).' WHERE lesson_id = (SELECT id FROM lessons WHERE order_index = 3) AND order_index = 2;
UPDATE exercises SET question = 'Vali number „viis“ (mari keeles).' WHERE lesson_id = (SELECT id FROM lessons WHERE order_index = 3) AND order_index = 3;
UPDATE exercises SET question = 'Kirjuta number „kümme“ (mari keeles).' WHERE lesson_id = (SELECT id FROM lessons WHERE order_index = 3) AND order_index = 4;
UPDATE exercises SET question = 'Vali number „kolm“ (mari keeles).' WHERE lesson_id = (SELECT id FROM lessons WHERE order_index = 3) AND order_index = 5;
