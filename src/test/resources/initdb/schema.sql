CREATE TABLE Tasks ( task_name VARCHAR(20), assignee VARCHAR(20), PRIMARY KEY (task_name, assignee));
CREATE TABLE ActivityHistories ( activity_id SERIAL PRIMARY KEY, detail VARCHAR(100) );
