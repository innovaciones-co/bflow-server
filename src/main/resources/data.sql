INSERT INTO Users (id, first_name, last_name, username, password, email, role, date_created, last_updated)
VALUES ( 10000, 'John', 'Doe', 'john_doe', '{bcrypt}$2a$10$MLHmPwZ8cLDOduP9V.9q1u8RzLCUgF0Xc8KHbMkp7mlxItMZVHdlO', 'john.doe@email.com', 'ADMIN', '2022-01-15T12:30:00Z', '2022-01-15T12:30:00Z');

INSERT INTO Users (id, first_name, last_name, username, password, email, role, date_created, last_updated)
VALUES ( 10001, 'Alice', 'Smith', 'alice_smith', '{bcrypt}$2a$10$MLHmPwZ8cLDOduP9V.9q1u8RzLCUgF0Xc8KHbMkp7mlxItMZVHdlO', 'alice.smith@email.com', 'ADMIN', '2022-01-15T13:45:00Z', '2022-01-15T13:45:00Z');

INSERT INTO Users (id, first_name, last_name, username, password, email, role, date_created, last_updated)
VALUES ( 10002, 'Bob', 'Johnson', 'bob_j', '{bcrypt}$2a$10$MLHmPwZ8cLDOduP9V.9q1u8RzLCUgF0Xc8KHbMkp7mlxItMZVHdlO', 'bob.johnson@email.com', 'ADMIN', '2022-01-15T14:15:00Z', '2022-01-15T14:15:00Z');

INSERT INTO Users (id, first_name, last_name, username, password, email, role, date_created, last_updated)
VALUES ( 10003, 'Eva', 'Williams', 'eva_w', '{bcrypt}$2a$10$MLHmPwZ8cLDOduP9V.9q1u8RzLCUgF0Xc8KHbMkp7mlxItMZVHdlO', 'eva.williams@email.com', 'ADMIN', '2022-01-15T15:00:00Z', '2022-01-15T15:00:00Z');

INSERT INTO Users (id, first_name, last_name, username, password, email, role, date_created, last_updated)
VALUES ( 10004, 'Charlie', 'Brown', 'charlie_b', '{bcrypt}$2a$10$MLHmPwZ8cLDOduP9V.9q1u8RzLCUgF0Xc8KHbMkp7mlxItMZVHdlO', 'charlie.brown@email.com', 'SUPERVISOR', '2022-01-15T16:30:00Z', '2022-01-15T16:30:00Z');

INSERT INTO Users (id, first_name, last_name, username, password, email, role, date_created, last_updated)
VALUES ( 10005, 'Alberto', 'Federico', 'alberto_f', '{bcrypt}$2a$10$MLHmPwZ8cLDOduP9V.9q1u8RzLCUgF0Xc8KHbMkp7mlxItMZVHdlO', 'afederico@email.com', 'SUPERVISOR', '2022-01-15T16:30:00Z', '2022-01-15T16:30:00Z');

INSERT INTO Contacts (id, name, address, email, type, date_created, last_updated)
VALUES (10000, 'John Doe', '123 Main St', 'john.doe@email.com', 'CLIENT', '2022-01-15T12:30:00Z', '2022-01-15T12:30:00Z');

INSERT INTO Contacts (id, name, address, email, type, date_created, last_updated)
VALUES (10001, 'Alice Smith', '456 Oak St', 'alice.smith@email.com', 'SUPPLIER', '2022-01-15T13:45:00Z', '2022-01-15T13:45:00Z');

INSERT INTO Contacts (id, name, address, email, type, date_created, last_updated)
VALUES (10002, 'Bob Johnson', '789 Pine St', 'bob.johnson@email.com', 'CLIENT', '2022-01-15T14:15:00Z', '2022-01-15T14:15:00Z');

INSERT INTO Contacts (id, name, address, email, type, date_created, last_updated)
VALUES (10003, 'Eva Williams', '101 Elm St', 'eva.williams@email.com', 'CONTRACTOR', '2022-01-15T15:00:00Z', '2022-01-15T15:00:00Z');

INSERT INTO Contacts (id, name, address, email, type, date_created, last_updated)
VALUES (10004, 'Charlie Brown', '202 Maple St', 'charlie.brown@email.com', 'SUPPLIER', '2022-01-15T16:30:00Z', '2022-01-15T16:30:00Z');

INSERT INTO Jobs (id, job_number, name, planned_start_date, planned_end_date, address, description, building_type, client_id, user_id, date_created, last_updated, supervisor_id)
VALUES (10000, 'J10001', 'Construction Project A', '2022-02-01', '2022-06-30', '123 Main St', 'A new building project', 'SINGLE_STOREY', 10000, 10000, '2022-01-15T12:30:00Z', '2022-01-15T12:30:00Z', '10005');

INSERT INTO Jobs (id, job_number, name, planned_start_date, planned_end_date, address, description, building_type, client_id, user_id, date_created, last_updated, supervisor_id)
VALUES (10001, 'J10002', 'Renovation Project B', '2022-03-15', '2022-08-30', '456 Oak St', 'Renovation of an existing property', 'RENOVATION', 10001, 10001, '2022-01-15T13:45:00Z', '2022-01-15T13:45:00Z', '10005');

INSERT INTO Jobs (id, job_number, name, planned_start_date, planned_end_date, address, description, building_type, client_id, user_id, date_created, last_updated, supervisor_id)
VALUES (10002, 'J10003', 'Shop Construction C', '2022-04-01', '2022-09-15', '789 Pine St', 'Construction of a commercial shop', 'SHOP', 10002, 10002, '2022-01-15T14:15:00Z', '2022-01-15T14:15:00Z', '10004');

INSERT INTO Jobs (id, job_number, name, planned_start_date, planned_end_date, address, description, building_type, client_id, user_id, date_created, last_updated, supervisor_id)
VALUES (10003, 'J10004', 'Double Storey House D', '2022-05-10', '2022-11-30', '101 Elm St', 'Construction of a double storey house', 'DOUBLE_STOREY', 10003, 10003, '2022-01-15T15:00:00Z', '2022-01-15T15:00:00Z', '10005');

INSERT INTO Jobs (id, job_number, name, planned_start_date, planned_end_date, address, description, building_type, client_id, user_id, date_created, last_updated, supervisor_id)
VALUES (10004, 'J10005', 'Extension Project E', '2022-06-15', '2023-01-15', '202 Maple St', 'Building an extension to an existing property', 'EXTENSION', 10004, 10004, '2022-01-15T16:30:00Z', '2022-01-15T16:30:00Z', '10005');

-- Sample Insert Note 1
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10000, 'Meeting with the client to discuss project requirements.', 10000, '2022-01-15T12:30:00Z', '2022-01-15T12:30:00Z');

-- Sample Insert Note 2
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10001, 'Received material delivery for the renovation project.', 10001, '2022-01-15T13:45:00Z', '2022-01-15T13:45:00Z');

-- Sample Insert Note 3
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10002, 'Inspection of the shop construction site.', 10002, '2022-01-15T14:15:00Z', '2022-01-15T14:15:00Z');

-- Sample Insert Note 4
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10003, 'Double storey house foundation work started.', 10003, '2022-01-15T15:00:00Z', '2022-01-15T15:00:00Z');

-- Sample Insert Note 5
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10004, 'Planning for the extension project.', 10004, '2022-01-15T16:30:00Z', '2022-01-15T16:30:00Z');

-- Sample Insert Note 6
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10005, 'Discussion with the architect about project design.', 10000, '2022-01-16T09:00:00Z', '2022-01-16T09:00:00Z');

-- Sample Insert Note 7
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10006, 'Completed interior painting for the renovation project.', 10001, '2022-01-17T14:30:00Z', '2022-01-17T14:30:00Z');

-- Sample Insert Note 8
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10007, 'Reviewed construction progress for the shop project.', 10002, '2022-01-18T11:45:00Z', '2022-01-18T11:45:00Z');

-- Sample Insert Note 9
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10008, 'Pouring concrete for the double storey house foundation.', 10003, '2022-01-19T08:15:00Z', '2022-01-19T08:15:00Z');

-- Sample Insert Note 10
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10009, 'Discussing budget details for the extension project.', 10004, '2022-01-20T13:00:00Z', '2022-01-20T13:00:00Z');

-- Sample Insert Note 11
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10010, 'Site visit with the client to finalize project requirements.', 10000, '2022-01-21T10:30:00Z', '2022-01-21T10:30:00Z');

-- Sample Insert Note 12
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10011, 'Installed new flooring for the renovation project.', 10001, '2022-01-22T15:45:00Z', '2022-01-22T15:45:00Z');

-- Sample Insert Note 13
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10012, 'Meeting with suppliers for materials for the shop project.', 10002, '2022-01-23T11:00:00Z', '2022-01-23T11:00:00Z');

-- Sample Insert Note 14
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10013, 'Completed framing for the double storey house.', 10003, '2022-01-24T09:30:00Z', '2022-01-24T09:30:00Z');

-- Sample Insert Note 15
INSERT INTO Notes (id, body, job_id, date_created, last_updated)
VALUES (10014, 'Discussion with the client about the extension project timeline.', 10004, '2022-01-25T12:00:00Z', '2022-01-25T12:00:00Z');

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

INSERT INTO templates (id, name, template, date_created, last_updated) VALUES (10001, 'Job Template', '[
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
]', now(), now())