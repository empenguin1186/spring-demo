CREATE TABLE Tasks ( task_name VARCHAR(20), assigned VARCHAR(20), PRIMARY KEY (task_name, assigned));
CREATE TABLE ActivityHistories ( activity_id SERIAL PRIMARY KEY, detail VARCHAR(100) );