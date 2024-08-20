INSERT INTO Users (id, first_name, last_name, username, password, email, role, date_created, last_updated)
VALUES ( 10000, 'John', 'Doe', 'john_doe', '{bcrypt}$2a$10$MLHmPwZ8cLDOduP9V.9q1u8RzLCUgF0Xc8KHbMkp7mlxItMZVHdlO', 'john.doe@email.com', 'ADMIN', '2024-01-15T12:30:00Z', '2024-01-15T12:30:00Z');

INSERT INTO Users (id, first_name, last_name, username, password, email, role, date_created, last_updated)
VALUES ( 10001, 'Alice', 'Smith', 'alice_smith', '{bcrypt}$2a$10$MLHmPwZ8cLDOduP9V.9q1u8RzLCUgF0Xc8KHbMkp7mlxItMZVHdlO', 'alice.smith@email.com', 'ADMIN', '2024-01-15T13:45:00Z', '2024-01-15T13:45:00Z');

INSERT INTO Users (id, first_name, last_name, username, password, email, role, date_created, last_updated)
VALUES ( 10002, 'Bob', 'Johnson', 'bob_j', '{bcrypt}$2a$10$MLHmPwZ8cLDOduP9V.9q1u8RzLCUgF0Xc8KHbMkp7mlxItMZVHdlO', 'bob.johnson@email.com', 'ADMIN', '2024-01-15T14:15:00Z', '2024-01-15T14:15:00Z');

INSERT INTO Users (id, first_name, last_name, username, password, email, role, date_created, last_updated)
VALUES ( 10003, 'Eva', 'Williams', 'eva_w', '{bcrypt}$2a$10$MLHmPwZ8cLDOduP9V.9q1u8RzLCUgF0Xc8KHbMkp7mlxItMZVHdlO', 'eva.williams@email.com', 'ADMIN', '2024-01-15T15:00:00Z', '2024-01-15T15:00:00Z');

INSERT INTO Users (id, first_name, last_name, username, password, email, role, date_created, last_updated)
VALUES ( 10004, 'Charlie', 'Brown', 'charlie_b', '{bcrypt}$2a$10$MLHmPwZ8cLDOduP9V.9q1u8RzLCUgF0Xc8KHbMkp7mlxItMZVHdlO', 'charlie.brown@email.com', 'SUPERVISOR', '2024-01-15T16:30:00Z', '2024-01-15T16:30:00Z');

INSERT INTO Users (id, first_name, last_name, username, password, email, role, date_created, last_updated)
VALUES ( 10005, 'Alberto', 'Federico', 'alberto_f', '{bcrypt}$2a$10$MLHmPwZ8cLDOduP9V.9q1u8RzLCUgF0Xc8KHbMkp7mlxItMZVHdlO', 'afederico@email.com', 'SUPERVISOR', '2024-01-15T16:30:00Z', '2024-01-15T16:30:00Z');

INSERT INTO Users (id, first_name, last_name, username, password, email, role, date_created, last_updated)
VALUES  (10006, 'Sergio', 'Torres', 'sdtorresl', '{bcrypt}$2a$10$pLpMYr447etWZVn6tjCXgOab.Aef3.TlC2CEQKDH2WdRQWO6w/pbq', 'sdtorresl@innovaciones.co', 'ADMIN', '2024-06-09 23:36:43.691815 +00:00', '2024-06-09 23:36:43.691815 +00:00');

INSERT INTO Contacts (id, name, address, email, type, date_created, last_updated, phone)
VALUES (10000, 'John Doe', '123 Main St', 'john.doe@email.com', 'CLIENT', '2024-01-15T12:30:00Z', '2024-01-15T12:30:00Z', '123-456-7890');

INSERT INTO Contacts (id, name, address, email, type, date_created, last_updated, phone)
VALUES (10001, 'Alice Smith', '456 Oak St', 'alice.smith@email.com', 'SUPPLIER', '2024-01-15T13:45:00Z', '2024-01-15T13:45:00Z', '456-789-0123');

INSERT INTO Contacts (id, name, address, email, type, date_created, last_updated, phone)
VALUES (10002, 'Bob Johnson', '789 Pine St', 'bob.johnson@email.com', 'CLIENT', '2024-01-15T14:15:00Z', '2024-01-15T14:15:00Z', '789-012-3456');

INSERT INTO Contacts (id, name, address, email, type, date_created, last_updated, phone)
VALUES (10003, 'Eva Williams', '101 Elm St', 'eva.williams@email.com', 'CONTRACTOR', '2024-01-15T15:00:00Z', '2024-01-15T15:00:00Z', '321-654-9870');

INSERT INTO Contacts (id, name, address, email, type, date_created, last_updated, phone)
VALUES (10004, 'Charlie Brown', '202 Maple St', 'charlie.brown@email.com', 'SUPPLIER', '2024-01-15T16:30:00Z', '2024-01-15T16:30:00Z', '654-987-0123');

INSERT INTO Jobs (id, job_number, name, planned_start_date, planned_end_date, address, description, building_type, client_id, user_id, date_created, last_updated, supervisor_id)
VALUES (9999, 'J9999', 'Nemo''s House', '2024-02-01', '2024-06-30', '123 Main St', 'The house of Nemo', 'SINGLE_STOREY', 10000, 10000, '2024-01-15T12:30:00Z', '2024-01-15T12:30:00Z', '10005');


INSERT INTO Jobs (id, job_number, name, planned_start_date, planned_end_date, address, description, building_type, client_id, user_id, date_created, last_updated, supervisor_id)
VALUES (10000, 'J10001', 'Construction Project A', '2024-02-01', '2024-06-30', '123 Main St', 'A new building project', 'SINGLE_STOREY', 10000, 10000, '2024-01-15T12:30:00Z', '2024-01-15T12:30:00Z', '10005');

INSERT INTO Jobs (id, job_number, name, planned_start_date, planned_end_date, address, description, building_type, client_id, user_id, date_created, last_updated, supervisor_id)
VALUES (10001, 'J10002', 'Renovation Project B', '2024-03-15', '2024-08-30', '456 Oak St', 'Renovation of an existing property', 'RENOVATION', 10001, 10001, '2024-01-15T13:45:00Z', '2024-01-15T13:45:00Z', '10005');

INSERT INTO Jobs (id, job_number, name, planned_start_date, planned_end_date, address, description, building_type, client_id, user_id, date_created, last_updated, supervisor_id)
VALUES (10002, 'J10003', 'Shop Construction C', '2024-04-01', '2024-09-15', '789 Pine St', 'Construction of a commercial shop', 'SHOP', 10002, 10002, '2024-01-15T14:15:00Z', '2024-01-15T14:15:00Z', '10004');

INSERT INTO Jobs (id, job_number, name, planned_start_date, planned_end_date, address, description, building_type, client_id, user_id, date_created, last_updated, supervisor_id)
VALUES (10003, 'J10004', 'Double Storey House D', '2024-05-10', '2024-11-30', '101 Elm St', 'Construction of a double storey house', 'DOUBLE_STOREY', 10003, 10003, '2024-01-15T15:00:00Z', '2024-01-15T15:00:00Z', '10005');

INSERT INTO Jobs (id, job_number, name, planned_start_date, planned_end_date, address, description, building_type, client_id, user_id, date_created, last_updated, supervisor_id)
VALUES (10004, 'J10005', 'Extension Project E', '2024-06-15', '2023-01-15', '202 Maple St', 'Building an extension to an existing property', 'EXTENSION', 10004, 10004, '2024-01-15T16:30:00Z', '2024-01-15T16:30:00Z', '10005');

-- Sample Insert Note 1
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10000, 'Meeting with the client to discuss project requirements.', 10000, '2024-01-15T12:30:00Z', '2024-01-15T12:30:00Z');

-- Sample Insert Note 2
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10001, 'Received material delivery for the renovation project.', 10001, '2024-01-15T13:45:00Z', '2024-01-15T13:45:00Z');

-- Sample Insert Note 3
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10002, 'Inspection of the shop construction site.', 10002, '2024-01-15T14:15:00Z', '2024-01-15T14:15:00Z');

-- Sample Insert Note 4
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10003, 'Double storey house foundation work started.', 10003, '2024-01-15T15:00:00Z', '2024-01-15T15:00:00Z');

-- Sample Insert Note 5
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10004, 'Planning for the extension project.', 10004, '2024-01-15T16:30:00Z', '2024-01-15T16:30:00Z');

-- Sample Insert Note 6
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10005, 'Discussion with the architect about project design.', 10000, '2024-01-16T09:00:00Z', '2024-01-16T09:00:00Z');

-- Sample Insert Note 7
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10006, 'Completed interior painting for the renovation project.', 10001, '2024-01-17T14:30:00Z', '2024-01-17T14:30:00Z');

-- Sample Insert Note 8
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10007, 'Reviewed construction progress for the shop project.', 10002, '2024-01-18T11:45:00Z', '2024-01-18T11:45:00Z');

-- Sample Insert Note 9
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10008, 'Pouring concrete for the double storey house foundation.', 10003, '2024-01-19T08:15:00Z', '2024-01-19T08:15:00Z');

-- Sample Insert Note 10
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10009, 'Discussing budget details for the extension project.', 10004, '2024-01-20T13:00:00Z', '2024-01-20T13:00:00Z');

-- Sample Insert Note 11
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10010, 'Site visit with the client to finalize project requirements.', 10000, '2024-01-21T10:30:00Z', '2024-01-21T10:30:00Z');

-- Sample Insert Note 12
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10011, 'Installed new flooring for the renovation project.', 10001, '2024-01-22T15:45:00Z', '2024-01-22T15:45:00Z');

-- Sample Insert Note 13
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10012, 'Meeting with suppliers for materials for the shop project.', 10002, '2024-01-23T11:00:00Z', '2024-01-23T11:00:00Z');

-- Sample Insert Note 14
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10013, 'Completed framing for the double storey house.', 10003, '2024-01-24T09:30:00Z', '2024-01-24T09:30:00Z');

-- Sample Insert Note 15
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10014, 'Discussion with the client about the extension project timeline.', 10004, '2024-01-25T12:00:00Z', '2024-01-25T12:00:00Z');

INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-01-30', 75, '2024-01-26', null, '2024-01-15 14:15:00.000000 +00:00', 10002, 10001, '2024-01-15 14:15:00.000000 +00:00', null, 10002, null, 'Formwork Installation', 'SLAB_DOWN', 'IN_PROGRESS');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-02-05', 100, '2024-02-01', null, '2024-01-15 15:00:00.000000 +00:00', 10003, 10002, '2024-01-15 15:00:00.000000 +00:00', null, 10003, null, 'Steel Reinforcement', 'SLAB_DOWN', 'COMPLETED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-02-10', 0, '2024-02-06', null, '2024-01-15 16:30:00.000000 +00:00', 10004, 10001, '2024-01-15 16:30:00.000000 +00:00', null, null, null, 'Slab Inspection', 'SLAB_DOWN', 'CREATED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-02-15', 10, '2024-02-11', null, '2024-01-15 17:45:00.000000 +00:00', 10005, 10002, '2024-01-15 17:45:00.000000 +00:00', null, 10000, null, 'Walls Construction', 'PLATE_HEIGH', 'IN_PROGRESS');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-02-08', 20, '2024-02-01', null, '2024-01-16 09:00:00.000000 +00:00', 10006, 10001, '2024-01-16 09:00:00.000000 +00:00', null, 10001, null, 'Roof Trusses Installation', 'PLATE_HEIGH', 'IN_PROGRESS');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-02-25', 30, '2024-02-08', null, '2024-01-17 14:30:00.000000 +00:00', 10007, 10002, '2024-01-17 14:30:00.000000 +00:00', null, 10002, null, 'Roof Covering', 'ROOF_COVER', 'IN_PROGRESS');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-03-01', 40, '2024-02-26', null, '2024-01-18 11:45:00.000000 +00:00', 10008, 10001, '2024-01-18 11:45:00.000000 +00:00', null, 10003, null, 'Lock Up', 'LOCK_UP', 'IN_PROGRESS');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-03-05', 50, '2024-03-02', null, '2024-01-19 08:15:00.000000 +00:00', 10009, 10002, '2024-01-19 08:15:00.000000 +00:00', null, 10004, null, 'Cabinets Design', 'CABINETS', 'CREATED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-03-07', 50, '2024-03-03', null, '2024-01-20 13:00:00.000000 +00:00', 10010, 10002, '2024-01-20 13:00:00.000000 +00:00', 10009, null, null, 'Cabinets Approval', 'CABINETS', 'CREATED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-03-15', 30, '2024-03-23', null, '2024-01-21 10:30:00.000000 +00:00', 10011, 10002, '2024-01-21 10:30:00.000000 +00:00', 10009, 10004, null, 'Cabinets Construction', 'CABINETS', 'CREATED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-03-15', 15, '2024-03-16', null, '2024-01-22 15:45:00.000000 +00:00', 10012, 10001, '2024-01-22 15:45:00.000000 +00:00', 10004, null, null, 'Cabinets Installation', 'CABINETS', 'IN_PROGRESS');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-03-25', 0, '2024-03-21', null, '2024-01-23 11:00:00.000000 +00:00', 10013, 10002, '2024-01-23 11:00:00.000000 +00:00', null, 10000, null, 'PCI Planning', 'PCI', 'CREATED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-03-30', 0, '2024-03-26', null, '2024-01-24 09:30:00.000000 +00:00', 10014, 10002, '2024-01-24 09:30:00.000000 +00:00', 10013, 10001, null, 'PCI Inspection', 'PCI', 'SENT');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-04-05', 0, '2024-04-01', null, '2024-01-25 12:00:00.000000 +00:00', 10015, 10002, '2024-01-25 12:00:00.000000 +00:00', 10003, null, null, 'PCI Approval', 'PCI', 'RESCHEDULE');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-04-10', 0, '2024-04-06', null, '2024-01-26 13:15:00.000000 +00:00', 10016, 10002, '2024-01-26 13:15:00.000000 +00:00', 10003, null, null, 'Final Touches', 'PCI', 'CREATED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-04-15', 0, '2024-04-03', null, '2024-01-27 10:45:00.000000 +00:00', 10017, 10001, '2024-01-27 10:45:00.000000 +00:00', 10002, null, null, 'Project Completion', 'PCI', 'CREATED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-04-20', 0, '2024-04-16', null, '2024-01-28 09:30:00.000000 +00:00', 10018, 10001, '2024-01-28 09:30:00.000000 +00:00', null, 10002, null, 'Task 18', 'LOCK_UP', 'COMPLETED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-04-25', 0, '2024-04-21', null, '2024-01-29 14:00:00.000000 +00:00', 10019, 10002, '2024-01-29 14:00:00.000000 +00:00', null, 10003, null, 'Task 19', 'LOCK_UP', 'COMPLETED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-04-30', 0, '2024-04-26', null, '2024-01-30 11:15:00.000000 +00:00', 10020, 10001, '2024-01-30 11:15:00.000000 +00:00', null, 10004, null, 'Task 20', 'LOCK_UP', 'CREATED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-01-10', 0, '2024-01-06', null, '2024-01-03 08:30:00.000000 +00:00', 10021, 10001, '2024-01-03 08:30:00.000000 +00:00', 10005, null, null, 'Foundation Excavation', 'SLAB_DOWN', 'CREATED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-01-15', 0, '2024-01-11', null, '2024-01-04 09:45:00.000000 +00:00', 10022, 10001, '2024-01-04 09:45:00.000000 +00:00', 10006, 10001, null, 'Foundation Pouring', 'SLAB_DOWN', 'CREATED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-01-20', 30, '2024-01-16', null, '2024-01-05 10:00:00.000000 +00:00', 10023, 10002, '2024-01-05 10:00:00.000000 +00:00', null, 10003, null, 'Frame Construction', 'PLATE_HEIGH', 'IN_PROGRESS');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-01-25', 0, '2024-01-21', null, '2024-01-06 11:15:00.000000 +00:00', 10024, 10003, '2024-01-06 11:15:00.000000 +00:00', 10008, null, null, 'Roof Framing', 'ROOF_COVER', 'CREATED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-02-01', 70, '2024-01-28', null, '2024-01-07 12:30:00.000000 +00:00', 10025, 10004, '2024-01-07 12:30:00.000000 +00:00', null, 10004, null, 'Cabinet Installation', 'CABINETS', 'IN_PROGRESS');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-02-10', 0, '2024-02-06', null, '2024-01-08 13:45:00.000000 +00:00', 10026, 10001, '2024-01-08 13:45:00.000000 +00:00', null, 10002, null, 'Final Inspection', 'PCI', 'CREATED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-02-15', 0, '2024-02-11', null, '2024-01-09 14:00:00.000000 +00:00', 10027, 10002, '2024-01-09 14:00:00.000000 +00:00', 10009, null, null, 'HVAC Installation', 'PCI', 'CREATED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-02-20', 50, '2024-02-16', null, '2024-01-10 15:15:00.000000 +00:00', 10028, 10003, '2024-01-10 15:15:00.000000 +00:00', null, 10001, null, 'Electrical Wiring', 'PLATE_HEIGH', 'IN_PROGRESS');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-02-25', 0, '2024-02-21', null, '2024-01-11 16:30:00.000000 +00:00', 10029, 10004, '2024-01-11 16:30:00.000000 +00:00', null, 10001, null, 'Window Installation', 'ROOF_COVER', 'CREATED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-03-01', 80, '2024-02-26', null, '2024-01-12 17:45:00.000000 +00:00', 10030, 10002, '2024-01-12 17:45:00.000000 +00:00', null, 10003, null, 'Exterior Painting', 'LOCK_UP', 'IN_PROGRESS');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-03-05', 0, '2024-03-02', null, '2024-01-13 08:00:00.000000 +00:00', 10031, 10003, '2024-01-13 08:00:00.000000 +00:00', 10010, null, null, 'Plumbing Installation', 'LOCK_UP', 'CREATED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-03-15', 0, '2024-03-11', null, '2024-01-14 09:15:00.000000 +00:00', 10032, 10001, '2024-01-14 09:15:00.000000 +00:00', 10011, null, null, 'Landscaping', 'LOCK_UP', 'CREATED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-03-20', 0, '2024-03-16', null, '2024-01-15 10:30:00.000000 +00:00', 10033, 10002, '2024-01-15 10:30:00.000000 +00:00', null, 10002, null, 'Task 33', 'LOCK_UP', 'CREATED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-03-25', 0, '2024-03-21', null, '2024-01-16 11:45:00.000000 +00:00', 10034, 10001, '2024-01-16 11:45:00.000000 +00:00', null, 10003, null, 'Task 34', 'LOCK_UP', 'CREATED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-04-01', 0, '2024-03-28', null, '2024-01-17 13:00:00.000000 +00:00', 10035, 10002, '2024-01-17 13:00:00.000000 +00:00', 10012, null, null, 'Task 35', 'PCI', 'CREATED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-04-05', 0, '2024-04-01', null, '2024-01-18 14:15:00.000000 +00:00', 10036, 10003, '2024-01-18 14:15:00.000000 +00:00', 10013, null, null, 'Task 36', 'PCI', 'CREATED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-04-10', 0, '2024-04-06', null, '2024-01-19 15:30:00.000000 +00:00', 10037, 10001, '2024-01-19 15:30:00.000000 +00:00', null, 10004, null, 'Task 37', 'PCI', 'CANCELED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-04-15', 0, '2024-04-11', null, '2024-01-20 16:45:00.000000 +00:00', 10038, 10002, '2024-01-20 16:45:00.000000 +00:00', 10014, null, null, 'Task 38', 'PCI', 'CREATED');
INSERT INTO Tasks (end_date, progress, start_date, call_date, date_created, id, job_id, last_updated, parent_task_id, supplier_id, description, name, stage, status) VALUES ('2024-04-25', 0, '2024-04-21', null, '2024-01-22 09:15:00.000000 +00:00', 10040, 10004, '2024-01-22 09:15:00.000000 +00:00', null, 10001, null, 'Task 40', 'PCI', 'CREATED');

-- Insert categories
INSERT INTO Categories (id, name, trade_code, date_created, last_updated)
VALUES (1, 'Wood', 100, NOW(), NOW()),
       (2, 'Metal', 200, NOW(), NOW()),
       (3, 'Concrete', 300, NOW(), NOW()),
       (4, 'Plumbing', 400, NOW(), NOW()),
       (5, 'Electrical', 500, NOW(), NOW());

-- Insert products for suppliers
INSERT INTO products (id, name, description, unit_price, uom_order_increment, unit_of_measure, category_id, date_created, last_updated, sku, supplier_id)
VALUES
    -- Wood products
    (1, 'Lumber', 'Various sizes of lumber for construction', 20.00, 10, 'FEET', 1, NOW(), NOW(), 'WOOD001', 10001),
    (2, 'Plywood', 'Sheets of plywood for building', 25.00, 1, 'SQUARE_FEET', 1, NOW(), NOW(), 'WOOD002', 10001),
    (3, 'Wood Screws', 'Assorted wood screws for fastening', 0.10, 100, 'PIECES', 1, NOW(), NOW(), 'WOOD003', 10001),
    -- Metal products
    (4, 'Steel Beams', 'Structural steel beams for support', 50.00, 5, 'FEET', 2, NOW(), NOW(), 'METAL001', 10001),
    (5, 'Aluminum Sheets', 'Sheets of aluminum for siding', 30.00, 1, 'SQUARE_FEET', 2, NOW(), NOW(), 'METAL002', 10001),
    (6, 'Nails', 'Assorted nails for metal framing', 0.05, 200, 'PIECES', 2, NOW(), NOW(), 'METAL003', 10001),
    -- Concrete products
    (7, 'Concrete Blocks', 'Blocks for construction and landscaping', 3.00, 1, 'EACH', 3, NOW(), NOW(), 'CONCRETE001', 10001),
    (8, 'Ready-Mix Concrete', 'Pre-mixed concrete for pouring', 100.00, 1, 'CUBIC_METERS', 3, NOW(), NOW(), 'CONCRETE002', 10001),
    (9, 'Rebar', 'Steel reinforcement bars for concrete', 2.00, 10, 'FEET', 3, NOW(), NOW(), 'CONCRETE003', 10001),
    -- Plumbing products
    (10, 'PVC Pipes', 'Pipes for plumbing installations', 2.50, 1, 'FEET', 4, NOW(), NOW(), 'PLUMBING001', 10004),
    (11, 'Faucets', 'Various faucets for sinks and showers', 30.00, 1, 'EACH', 4, NOW(), NOW(), 'PLUMBING002', 10004),
    (12, 'Pipe Fittings', 'Assorted fittings for plumbing systems', 1.00, 50, 'PIECES', 4, NOW(), NOW(), 'PLUMBING003', 10004),
    -- Electrical products
    (13, 'Electrical Wire', 'Wire for electrical installations', 0.75, 100, 'FEET', 5, NOW(), NOW(), 'ELECTRICAL001', 10004),
    (14, 'Light Fixtures', 'Various light fixtures for indoor and outdoor use', 50.00, 1, 'EACH', 5, NOW(), NOW(), 'ELECTRICAL002', 10004),
    (15, 'Circuit Breakers', 'Breakers for electrical panels', 20.00, 1, 'EACH', 5, NOW(), NOW(), 'ELECTRICAL003', 10004);

-- Generate sample purchase orders and items
insert into public.purchase_orders (approved_date, completed_date, date_created, id, job_id, last_updated, sent_date, supplier_id, number, status)
values  (null, null, '2024-04-21 01:20:54.021014 +00:00', 2, 10001, '2024-04-21 01:20:54.021014 +00:00', null, 10001, 'PO002', 'DRAFT'),
        (null, null, '2024-04-21 01:20:54.021014 +00:00', 3, 10002, '2024-04-21 01:20:54.021014 +00:00', null, 10001, 'PO003', 'DRAFT'),
        (null, null, '2024-04-21 01:20:54.021014 +00:00', 1, 10001, '2024-04-21 01:20:54.021014 +00:00', null, 10001, 'PO001', 'DRAFT');

-- Insert items for each purchase order
INSERT INTO items (id, name, description, unit_price, units, category_id, date_created, last_updated, purchase_order_id, supplier_id, job_id, measure)
VALUES
    -- Purchase Order 1 items
    (1, 'Lumber', 'Various sizes of lumber for construction', 20.00, 2, 1, NOW(), NOW(), 1, 10001, 10001, 'SQUARE_METERS'), -- Supplier: Alice Smith
    (2, 'Steel Beams', 'Structural steel beams for support', 50.00, 4, 2, NOW(), NOW(), 1, 10001, 10001, 'SQUARE_METERS'), -- Supplier: Alice Smith
    (3, 'Concrete Blocks', 'Blocks for construction and landscaping', 3.00, 5, 3, NOW(), NOW(), 1, 10001, 10001, 'EACH'), -- Supplier: Alice Smith
    -- Purchase Order 2 items
    (4, 'Plywood', 'Sheets of plywood for building', 25.00, 10, 1, NOW(), NOW(), 2, 10004, 10001, 'EACH'), -- Supplier: Charlie Brown
    (5, 'Aluminum Sheets', 'Sheets of aluminum for siding', 30.00, 5, 2, NOW(), NOW(), 2, 10004, 10001, 'EACH'), -- Supplier: Charlie Brown
    (6, 'Ready-Mix Concrete', 'Pre-mixed concrete for pouring', 100.00, 8, 3, NOW(), NOW(), 2, 10004, 10001, 'KILOGRAMS'), -- Supplier: Charlie Brown
    -- Purchase Order 3 items
    (7, 'Wood Screws', 'Assorted wood screws for fastening', 0.10, 1, 1, NOW(), NOW(), 3, 10002, 10001, 'EACH'), -- Supplier: Bob Johnson
    (8, 'Nails', 'Assorted nails for metal framing', 0.05, 5, 2, NOW(), NOW(), 3, 10002, 10001, 'EACH'), -- Supplier: Bob Johnson
    (9, 'Rebar', 'Steel reinforcement bars for concrete', 2.00, 4, 3, NOW(), NOW(), 3, 10002, 10001, 'METERS'); -- Supplier: Bob Johnson


INSERT INTO Templates (id, type, name, template, date_created, last_updated) VALUES (10001, 'TASK_TEMPLATE', 'Job Template', '[
  {
    "stage": "SLAB_DOWN",
    "status": "CREATED",
    "name": "Formwork Installation",
    "description": null,
    "supplier_id": 10002
  },
  {
    "stage": "SLAB_DOWN",
    "status": "CREATED",
    "name": "Steel Reinforcement",
    "description": null,
    "supplier_id": 10003
  },
  {
    "stage": "SLAB_DOWN",
    "status": "CREATED",
    "name": "Slab Inspection",
    "description": null,
    "supplier_id": null
  },
  {
    "stage": "PLATE_HEIGH",
    "status": "CREATED",
    "name": "Walls Construction",
    "description": null,
    "supplier_id": 10000
  },
  {
    "stage": "PLATE_HEIGH",
    "status": "CREATED",
    "name": "Roof Trusses Installation",
    "description": null,
    "supplier_id": 10001
  },
  {
    "stage": "ROOF_COVER",
    "status": "CREATED",
    "name": "Roof Covering",
    "description": null,
    "supplier_id": 10002
  },
  {
    "stage": "LOCK_UP",
    "status": "CREATED",
    "name": "Lock Up",
    "description": null,
    "supplier_id": 10003
  },
  {
    "stage": "CABINETS",
    "status": "CREATED",
    "name": "Cabinets Design",
    "description": null,
    "supplier_id": 10004
  },
  {
    "stage": "CABINETS",
    "status": "CREATED",
    "name": "Cabinets Approval",
    "description": null,
    "supplier_id": null
  },
  {
    "stage": "CABINETS",
    "status": "CREATED",
    "name": "Cabinets Construction",
    "description": null,
    "supplier_id": 10004
  },
  {
    "stage": "CABINETS",
    "status": "CREATED",
    "name": "Cabinets Installation",
    "description": null,
    "supplier_id": null
  },
  {
    "stage": "PCI",
    "status": "CREATED",
    "name": "PCI Planning",
    "description": null,
    "supplier_id": 10000
  },
  {
    "stage": "PCI",
    "status": "CREATED",
    "name": "PCI Inspection",
    "description": null,
    "supplier_id": 10001
  },
  {
    "stage": "PCI",
    "status": "CREATED",
    "name": "PCI Approval",
    "description": null,
    "supplier_id": null
  },
  {
    "stage": "PCI",
    "status": "CREATED",
    "name": "Final Touches",
    "description": null,
    "supplier_id": null
  },
  {
    "stage": "PCI",
    "status": "CREATED",
    "name": "Project Completion",
    "description": null,
    "supplier_id": null
  },
  {
    "stage": "LOCK_UP",
    "status": "CREATED",
    "name": "Task 18",
    "description": null,
    "supplier_id": 10002
  },
  {
    "stage": "LOCK_UP",
    "status": "CREATED",
    "name": "Task 19",
    "description": null,
    "supplier_id": 10003
  },
  {
    "stage": "LOCK_UP",
    "status": "CREATED",
    "name": "Task 20",
    "description": null,
    "supplier_id": 10004
  },
  {
    "stage": "SLAB_DOWN",
    "status": "CREATED",
    "name": "Foundation Excavation",
    "description": null,
    "supplier_id": null
  },
  {
    "stage": "SLAB_DOWN",
    "status": "CREATED",
    "name": "Foundation Pouring",
    "description": null,
    "supplier_id": 10001
  },
  {
    "stage": "PLATE_HEIGH",
    "status": "CREATED",
    "name": "Frame Construction",
    "description": null,
    "supplier_id": 10003
  },
  {
    "stage": "ROOF_COVER",
    "status": "CREATED",
    "name": "Roof Framing",
    "description": null,
    "supplier_id": null
  },
  {
    "stage": "CABINETS",
    "status": "CREATED",
    "name": "Cabinet Installation",
    "description": null,
    "supplier_id": 10004
  },
  {
    "stage": "PCI",
    "status": "CREATED",
    "name": "Final Inspection",
    "description": null,
    "supplier_id": 10002
  },
  {
    "stage": "PCI",
    "status": "CREATED",
    "name": "HVAC Installation",
    "description": null,
    "supplier_id": null
  },
  {
    "stage": "PLATE_HEIGH",
    "status": "CREATED",
    "name": "Electrical Wiring",
    "description": null,
    "supplier_id": 10001
  },
  {
    "stage": "ROOF_COVER",
    "status": "CREATED",
    "name": "Window Installation",
    "description": null,
    "supplier_id": 10001
  },
  {
    "stage": "LOCK_UP",
    "status": "CREATED",
    "name": "Exterior Painting",
    "description": null,
    "supplier_id": 10003
  },
  {
    "stage": "LOCK_UP",
    "status": "CREATED",
    "name": "Plumbing Installation",
    "description": null,
    "supplier_id": null
  },
  {
    "stage": "LOCK_UP",
    "status": "CREATED",
    "name": "Landscaping",
    "description": null,
    "supplier_id": null
  },
  {
    "stage": "LOCK_UP",
    "status": "CREATED",
    "name": "Task 33",
    "description": null,
    "supplier_id": 10002
  },
  {
    "stage": "LOCK_UP",
    "status": "CREATED",
    "name": "Task 34",
    "description": null,
    "supplier_id": 10003
  },
  {
    "stage": "PCI",
    "status": "CREATED",
    "name": "Task 35",
    "description": null,
    "supplier_id": null
  },
  {
    "stage": "PCI",
    "status": "CREATED",
    "name": "Task 36",
    "description": null,
    "supplier_id": null
  },
  {
    "stage": "PCI",
    "status": "CREATED",
    "name": "Task 37",
    "description": null,
    "supplier_id": 10004
  },
  {
    "stage": "PCI",
    "status": "CREATED",
    "name": "Task 38",
    "description": null,
    "supplier_id": null
  },
  {
    "stage": "PCI",
    "status": "CREATED",
    "name": "Task 40",
    "description": null,
    "supplier_id": 10001
  }
]', now(), now());

INSERT INTO Templates (id, type, name, template, date_created, last_updated) VALUES (10002, 'TASK_TEMPLATE', 'Single Storey', '[{"name":"Concrete Curing","status":"CREATED","stage":"SLAB_DOWN","order":1},{"name":"Drains & Soakwells","status":"CREATED","stage":"SLAB_DOWN","order":2},{"name":"Open trench Water & Gas","status":"CREATED","stage":"SLAB_DOWN","order":3},{"name":"Open trench Power","status":"CREATED","stage":"SLAB_DOWN","order":4},{"name":"Bobcat Slab Clean","status":"CREATED","stage":"SLAB_DOWN","order":5},{"name":"WC        ON HIRE","status":"CREATED","stage":"SLAB_DOWN","order":6},{"name":"Boundry Nails in Footing","status":"CREATED","stage":"SLAB_DOWN","order":7},{"name":"Waterproof slab edge","status":"CREATED","stage":"SLAB_DOWN","order":8},{"name":"Window Frames","status":"CREATED","stage":"SLAB_DOWN","order":9},{"name":"Check for future rear access or load","status":"CREATED","stage":"SLAB_DOWN","order":10},{"name":"Brick Supplier","status":"CREATED","stage":"SLAB_DOWN","order":11},{"name":"Brick Delivery","status":"CREATED","stage":"SLAB_DOWN","order":12},{"name":"Lintels, Rods, Columns & Tbars","status":"CREATED","stage":"SLAB_DOWN","order":13},{"name":"Metal Door Frames","status":"CREATED","stage":"SLAB_DOWN","order":14},{"name":"Timber Door Frames","status":"CREATED","stage":"SLAB_DOWN","order":15},{"name":"Cavity Wall Insulation","status":"CREATED","stage":"SLAB_DOWN","order":16},{"name":"Shed    ON HIRE","status":"CREATED","stage":"SLAB_DOWN","order":17},{"name":"Brickies Hardware","status":"CREATED","stage":"SLAB_DOWN","order":18},{"name":"Cement      1st load","status":"CREATED","stage":"SLAB_DOWN","order":19},{"name":"Cement      2nd load","status":"CREATED","stage":"SLAB_DOWN","order":20},{"name":"Brickies Sand.  1st Load","status":"CREATED","stage":"SLAB_DOWN","order":21},{"name":"Brickies Sand.  2nd Load","status":"CREATED","stage":"SLAB_DOWN","order":22},{"name":"Brickies Kit      ON HIRE","status":"CREATED","stage":"SLAB_DOWN","order":23},{"name":"Bricklayer","status":"CREATED","stage":"SLAB_DOWN","order":24},{"name":"Termite Barrier to boundry","status":"CREATED","stage":"SLAB_DOWN","order":25},{"name":"Site Welding","status":"CREATED","stage":"SLAB_DOWN","order":26},{"name":"Crane Hire","status":"CREATED","stage":"SLAB_DOWN","order":27},{"name":"Shed    OFF HIRE","status":"CREATED","stage":"PLATE_HEIGH","order":1},{"name":"Brickies Kit    OFF HIRE","status":"CREATED","stage":"PLATE_HEIGH","order":2},{"name":"Site Clean 1","status":"CREATED","stage":"PLATE_HEIGH","order":3},{"name":"Brick cleaner","status":"CREATED","stage":"PLATE_HEIGH","order":4},{"name":"Scaffold or Roof Safe","status":"CREATED","stage":"PLATE_HEIGH","order":5},{"name":"Roof Steel Beams","status":"CREATED","stage":"PLATE_HEIGH","order":6},{"name":"Timber Roof Frame Supply","status":"CREATED","stage":"PLATE_HEIGH","order":7},{"name":"Fascia","status":"CREATED","stage":"PLATE_HEIGH","order":8},{"name":"Roof Carpenter","status":"CREATED","stage":"PLATE_HEIGH","order":9},{"name":"Crane Hire","status":"CREATED","stage":"PLATE_HEIGH","order":10},{"name":"Cupboards Ready for Measure","status":"CREATED","stage":"PLATE_HEIGH","order":11},{"name":"Appliances to Cabinet maker","status":"CREATED","stage":"PLATE_HEIGH","order":12},{"name":"Sinks & Basins to Cabinet maker","status":"CREATED","stage":"PLATE_HEIGH","order":13},{"name":"Wall Mixers for Tube out","status":"CREATED","stage":"PLATE_HEIGH","order":14},{"name":"Water Tube","status":"CREATED","stage":"PLATE_HEIGH","order":15},{"name":"Gas Tube","status":"CREATED","stage":"PLATE_HEIGH","order":16},{"name":"Electrical Tube","status":"CREATED","stage":"PLATE_HEIGH","order":17},{"name":"Alarm Systems","status":"CREATED","stage":"PLATE_HEIGH","order":18},{"name":"Scaffold or Roof Safe","status":"CREATED","stage":"PLATE_HEIGH","order":19},{"name":"Gutters install","status":"CREATED","stage":"PLATE_HEIGH","order":20},{"name":"Roof Cover","status":"CREATED","stage":"PLATE_HEIGH","order":21},{"name":"Skylights","status":"CREATED","stage":"PLATE_HEIGH","order":22},{"name":"Solar Panels","status":"CREATED","stage":"PLATE_HEIGH","order":23},{"name":"Aircon Pre Duct Install","status":"CREATED","stage":"ROOF_COVER","order":24},{"name":"Insulation String Up","status":"CREATED","stage":"ROOF_COVER","order":25},{"name":"Flexy Duct to vents","status":"CREATED","stage":"ROOF_COVER","order":26},{"name":"Plasterers Sand.  1st load","status":"CREATED","stage":"ROOF_COVER","order":27},{"name":"Plasterers Sand.  2nd load","status":"CREATED","stage":"ROOF_COVER","order":28},{"name":"Plaster Float","status":"CREATED","stage":"ROOF_COVER","order":29},{"name":"External Float","status":"CREATED","stage":"ROOF_COVER","order":30},{"name":"Plaster Set","status":"CREATED","stage":"ROOF_COVER","order":31},{"name":"Ceilings Fix","status":"CREATED","stage":"ROOF_COVER","order":32},{"name":"Window Glazing","status":"CREATED","stage":"ROOF_COVER","order":33},{"name":"Architectural Mouldings","status":"CREATED","stage":"ROOF_COVER","order":34},{"name":"Bobcat Clean 2","status":"CREATED","stage":"ROOF_COVER","order":35},{"name":"Brick Clean. CREAM BRICK","status":"CREATED","stage":"ROOF_COVER","order":36},{"name":"Underground Power Run","status":"CREATED","stage":"ROOF_COVER","order":37},{"name":"Underground Gas Run","status":"CREATED","stage":"ROOF_COVER","order":38},{"name":"Gas Meter Install","status":"CREATED","stage":"ROOF_COVER","order":39},{"name":"Lockup Hardware","status":"CREATED","stage":"ROOF_COVER","order":40},{"name":"Mouldings, Shelf, Doors","status":"CREATED","stage":"ROOF_COVER","order":41},{"name":"Cladding","status":"CREATED","stage":"ROOF_COVER","order":42},{"name":"Fixer - Lock up","status":"CREATED","stage":"ROOF_COVER","order":43},{"name":"Downlight Cutout","status":"CREATED","stage":"LOCK_UP","order":1},{"name":"Painter. Ceilings","status":"CREATED","stage":"LOCK_UP","order":2},{"name":"Painter. Seal Internal Walls","status":"CREATED","stage":"LOCK_UP","order":3},{"name":"Sani Gear to Site","status":"CREATED","stage":"LOCK_UP","order":4},{"name":"Plumbing Sani.","status":"CREATED","stage":"LOCK_UP","order":5},{"name":"Cupboards","status":"CREATED","stage":"LOCK_UP","order":6},{"name":"Stone Benchtop","status":"CREATED","stage":"LOCK_UP","order":7},{"name":"Glass Splashback","status":"CREATED","stage":"CABINETS","order":1},{"name":"Brick for hobs","status":"CREATED","stage":"CABINETS","order":2},{"name":"Tilers Sand","status":"CREATED","stage":"CABINETS","order":3},{"name":"Tiler Screeding","status":"CREATED","stage":"CABINETS","order":4},{"name":"External Texture Coat","status":"CREATED","stage":"CABINETS","order":5},{"name":"Downpipes to Textured Wall","status":"CREATED","stage":"CABINETS","order":6},{"name":"Waterproofing Showers","status":"CREATED","stage":"CABINETS","order":7},{"name":"Tiles Delivery","status":"CREATED","stage":"CABINETS","order":8},{"name":"Channel Grates delivery","status":"CREATED","stage":"CABINETS","order":9},{"name":"Tiler Start","status":"CREATED","stage":"CABINETS","order":10},{"name":"Tile Meeting with Client","status":"CREATED","stage":"CABINETS","order":11},{"name":"Screens, Mirrors & Robes","status":"CREATED","stage":"CABINETS","order":12},{"name":"Cornice to Overheads / Tiling","status":"CREATED","stage":"CABINETS","order":13},{"name":"Skirting Supply","status":"CREATED","stage":"CABINETS","order":14},{"name":"Fixer install skirting","status":"CREATED","stage":"CABINETS","order":15},{"name":"Stormwater connection","status":"CREATED","stage":"CABINETS","order":16},{"name":"Sleev''s under hardstand","status":"CREATED","stage":"CABINETS","order":17},{"name":"NBN Conduit under hardstand","status":"CREATED","stage":"CABINETS","order":18},{"name":"Site Clean. Grano prep","status":"CREATED","stage":"CABINETS","order":19},{"name":"Termite Spray under Garage","status":"CREATED","stage":"CABINETS","order":20},{"name":"Garage Concrete Hardstand","status":"CREATED","stage":"CABINETS","order":21},{"name":"Garage Sectional Door","status":"CREATED","stage":"CABINETS","order":22},{"name":"Appliances","status":"CREATED","stage":"CABINETS","order":23},{"name":"Hot Water Unit","status":"CREATED","stage":"CABINETS","order":24},{"name":"Electrical Final","status":"CREATED","stage":"CABINETS","order":25},{"name":"TV Antenna","status":"CREATED","stage":"CABINETS","order":26},{"name":"NBN set up","status":"CREATED","stage":"CABINETS","order":27},{"name":"Alarm, Security systems","status":"CREATED","stage":"CABINETS","order":28},{"name":"Plumbing Fixtures","status":"CREATED","stage":"CABINETS","order":29},{"name":"Plumbing Chrome.","status":"CREATED","stage":"CABINETS","order":30},{"name":"Gas Final","status":"CREATED","stage":"CABINETS","order":31},{"name":"Aircon Install & connect","status":"CREATED","stage":"CABINETS","order":32},{"name":"Insulation Ceiling Batts","status":"CREATED","stage":"CABINETS","order":33},{"name":"Rough clean out","status":"CREATED","stage":"CABINETS","order":34},{"name":"Sticker up ceiling","status":"CREATED","stage":"CABINETS","order":35},{"name":"Painter. Gloss Doors","status":"CREATED","stage":"CABINETS","order":36},{"name":"Painter. Internal Walls","status":"CREATED","stage":"CABINETS","order":37},{"name":"Kerbing & Footpath","status":"CREATED","stage":"CABINETS","order":38},{"name":"Paving Sand & Limestone","status":"CREATED","stage":"CABINETS","order":39},{"name":"Site Clean & Paving Prep","status":"CREATED","stage":"CABINETS","order":40},{"name":"Paving Bricks Material","status":"CREATED","stage":"CABINETS","order":41},{"name":"Paving Bricks Delivered","status":"CREATED","stage":"CABINETS","order":42},{"name":"Paver / Concrete","status":"CREATED","stage":"CABINETS","order":43},{"name":"Termite Perimeter Spray","status":"CREATED","stage":"CABINETS","order":44},{"name":"Maintenance","status":"CREATED","stage":"CABINETS","order":45},{"name":"House Clean","status":"CREATED","stage":"CABINETS","order":46},{"name":"Silicone Seal","status":"CREATED","stage":"CABINETS","order":47},{"name":"Flyscreens","status":"CREATED","stage":"CABINETS","order":48},{"name":"Blinds","status":"CREATED","stage":"CABINETS","order":49},{"name":"Carpet","status":"CREATED","stage":"CABINETS","order":50},{"name":"Timber Floors","status":"CREATED","stage":"CABINETS","order":51},{"name":"Roof Tile Final Check","status":"CREATED","stage":"CABINETS","order":52},{"name":"Landscaping","status":"CREATED","stage":"CABINETS","order":53},{"name":"Site Clean (Final)","status":"CREATED","stage":"CABINETS","order":54},{"name":"WC   OFF HIRE","status":"CREATED","stage":"CABINETS","order":55},{"name":"Paint Touch Ups","status":"CREATED","stage":"CABINETS","order":56},{"name":"First Client Inspection","status":"CREATED","stage":"CABINETS","order":57},{"name":"Re-Clean","status":"CREATED","stage":"CABINETS","order":58},{"name":"Handover","status":"CREATED","stage":"CABINETS","order":59}]', now(), now());

INSERT INTO Templates (id, type, name, template, date_created, last_updated) VALUES (10003, 'MATERIAL_TEMPLATE', 'Material Template 1', '[
  {
    "productName": "Lumber",
    "quantity": 10
  },
  {
    "productName": "Circuit Breakers",
    "quantity": 20
  }
]', now(), now())
