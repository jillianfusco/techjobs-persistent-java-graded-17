--Part 1
-- id int, employer varchar(255), name varchar(255), skills varchar(255)

--Part 2
SELECT name FROM employer WHERE location = "St. Louis City";

--Part 3
DROP TABLE job;
-- updated to include a Many-to-One relationship with Employer, introducing the employer_id foreign key column to DB

--Part 4
SELECT *
FROM skill
LEFT JOIN job_skills ON skill.id = job_skills.skills_id
WHERE job_skills.jobs_id IS NOT NULL ORDER BY name ASC;

