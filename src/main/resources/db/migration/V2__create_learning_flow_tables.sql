CREATE TABLE lessons (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    order_index INT NOT NULL,
    is_locked BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT uq_lessons_order_index UNIQUE (order_index)
);

CREATE TABLE exercises (
    id BIGSERIAL PRIMARY KEY,
    lesson_id BIGINT NOT NULL REFERENCES lessons(id) ON DELETE CASCADE,
    type VARCHAR(40) NOT NULL,
    question TEXT NOT NULL,
    correct_answer TEXT NOT NULL,
    options_json TEXT,
    order_index INT NOT NULL,
    CONSTRAINT uq_exercises_lesson_order UNIQUE (lesson_id, order_index)
);

CREATE TABLE user_attempts (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES app_users(id) ON DELETE CASCADE,
    lesson_id BIGINT NOT NULL REFERENCES lessons(id) ON DELETE CASCADE,
    exercise_id BIGINT NOT NULL REFERENCES exercises(id) ON DELETE CASCADE,
    user_answer TEXT NOT NULL,
    is_correct BOOLEAN NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE user_lesson_completions (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES app_users(id) ON DELETE CASCADE,
    lesson_id BIGINT NOT NULL REFERENCES lessons(id) ON DELETE CASCADE,
    completed_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT uq_user_lesson_completion UNIQUE (user_id, lesson_id)
);

INSERT INTO lessons (title, description, order_index, is_locked) VALUES
('Lesson 1: Greetings', 'Learn everyday greetings and polite phrases.', 1, FALSE),
('Lesson 2: Introductions', 'Introduce yourself with simple sentences.', 2, FALSE),
('Lesson 3: Numbers', 'Practice counting in basic contexts.', 3, FALSE);

INSERT INTO exercises (lesson_id, type, question, correct_answer, options_json, order_index) VALUES
(1, 'MULTIPLE_CHOICE', 'How do you say "Hello"?', 'Салам', '["Поро","Салам","Йумо"]', 1),
(1, 'TEXT_INPUT', 'Type the word for "Thank you".', 'Тау', NULL, 2),
(1, 'MULTIPLE_CHOICE', 'Choose "Good morning".', 'Шочмо', '["Ужара","Шочмо","Кечывал"]', 3),
(1, 'TEXT_INPUT', 'Type the word for "Bye".', 'Чеверын', NULL, 4),
(1, 'MULTIPLE_CHOICE', 'Which option means "Please"?', 'Пожалста', '["Пожалста","Огеш","Кужу"]', 5),

(2, 'TEXT_INPUT', 'Type "My name is ..."', 'Мыйын лумем ...', NULL, 1),
(2, 'MULTIPLE_CHOICE', 'Choose the phrase for "I am from Estonia".', 'Мон Эстоний гыч', '["Мон Эстоний гыч","Тыйын мо","Те кушто"]', 2),
(2, 'TEXT_INPUT', 'Type "I am a student".', 'Мон студент', NULL, 3),
(2, 'MULTIPLE_CHOICE', 'How do you ask "What is your name?"', 'Тыйын лумет мом?', '["Мом ышташ?","Тыйын лумет мом?","Кужу тунемаш?"]', 4),
(2, 'TEXT_INPUT', 'Type "Nice to meet you".', 'Палымаш поро', NULL, 5),

(3, 'MULTIPLE_CHOICE', 'Choose number "one".', 'ик', '["ик","кок","кум"]', 1),
(3, 'TEXT_INPUT', 'Type number "two".', 'кок', NULL, 2),
(3, 'MULTIPLE_CHOICE', 'Choose number "five".', 'вич', '["ныл","вич","лу"]', 3),
(3, 'TEXT_INPUT', 'Type number "ten".', 'лу', NULL, 4),
(3, 'MULTIPLE_CHOICE', 'Choose number "three".', 'кум', '["кум","канде","инде"]', 5);
