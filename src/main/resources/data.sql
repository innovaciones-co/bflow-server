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

-- Sample Insert Task 3
INSERT INTO Tasks (id, name, start_date, end_date, progress, status, stage, parent_task_id, supplier_id, job_id, date_created, last_updated)
VALUES (10002, 'Formwork Installation', '2022-01-26', '2022-01-30', 75.0, 'IN_PROGRESS', 'SLAB_DOWN', NULL, 10002, 10001, '2022-01-15T14:15:00Z', '2022-01-15T14:15:00Z');

-- Sample Insert Task 4
INSERT INTO Tasks (id, name, start_date, end_date, progress, status, stage, parent_task_id, supplier_id, job_id, date_created, last_updated)
VALUES (10003, 'Steel Reinforcement', '2022-02-01', '2022-02-05', 100.0, 'COMPLETED', 'SLAB_DOWN', NULL, 10003, 10002, '2022-01-15T15:00:00Z', '2022-01-15T15:00:00Z');

-- Sample Insert Task 5
INSERT INTO Tasks (id, name, start_date, end_date, progress, status, stage, parent_task_id, supplier_id, job_id, date_created, last_updated)
VALUES (10004, 'Slab Inspection', '2022-02-06', '2022-02-10', 0.0, 'CREATED', 'SLAB_DOWN', NULL, NULL, 10001, '2022-01-15T16:30:00Z', '2022-01-15T16:30:00Z');

-- Sample Insert Task 6
INSERT INTO Tasks (id, name, start_date, end_date, progress, status, stage, parent_task_id, supplier_id, job_id, date_created, last_updated)
VALUES (10005, 'Walls Construction', '2022-02-11', '2022-02-15', 10.0, 'IN_PROGRESS', 'PLATE_HEIGH', NULL, 10000, 10002, '2022-01-15T17:45:00Z', '2022-01-15T17:45:00Z');

-- Sample Insert Task 7
INSERT INTO Tasks (id, name, start_date, end_date, progress, status, stage, parent_task_id, supplier_id, job_id, date_created, last_updated)
VALUES (10006, 'Roof Trusses Installation', '2022-02-16', '2022-02-20', 20.0, 'IN_PROGRESS', 'PLATE_HEIGH', NULL, 10001, 10001, '2022-01-16T09:00:00Z', '2022-01-16T09:00:00Z');

-- Sample Insert Task 8
INSERT INTO Tasks (id, name, start_date, end_date, progress, status, stage, parent_task_id, supplier_id, job_id, date_created, last_updated)
VALUES (10007, 'Roof Covering', '2022-02-21', '2022-02-25', 30.0, 'IN_PROGRESS', 'ROOF_COVER', NULL, 10002, 10002, '2022-01-17T14:30:00Z', '2022-01-17T14:30:00Z');

-- Sample Insert Task 9
INSERT INTO Tasks (id, name, start_date, end_date, progress, status, stage, parent_task_id, supplier_id, job_id, date_created, last_updated)
VALUES (10008, 'Lock Up', '2022-02-26', '2022-03-01', 40.0, 'IN_PROGRESS', 'LOCK_UP', NULL, 10003, 10001, '2022-01-18T11:45:00Z', '2022-01-18T11:45:00Z');

-- Sample Insert Task 10
INSERT INTO Tasks (id, name, start_date, end_date, progress, status, stage, parent_task_id, supplier_id, job_id, date_created, last_updated)
VALUES (10009, 'Cabinets Design', '2022-03-02', '2022-03-05', 50.0, 'CREATED', 'CABINETS', NULL, 10004, 10002, '2022-01-19T08:15:00Z', '2022-01-19T08:15:00Z');

-- Sample Insert Task 11
INSERT INTO Tasks (id, name, start_date, end_date, progress, status, stage, parent_task_id, supplier_id, job_id, date_created, last_updated)
VALUES (10010, 'Cabinets Approval', '2022-03-06', '2022-03-10', 0.0, 'CREATED', 'CABINETS', 10009, NULL, 10001, '2022-01-20T13:00:00Z', '2022-01-20T13:00:00Z');

-- Sample Insert Task 12
INSERT INTO Tasks (id, name, start_date, end_date, progress, status, stage, parent_task_id, supplier_id, job_id, date_created, last_updated)
VALUES (10011, 'Cabinets Construction', '2022-03-11', '2022-03-15', 0.0, 'CREATED', 'CABINETS', 10009, 10004, 10002, '2022-01-21T10:30:00Z', '2022-01-21T10:30:00Z');

-- Sample Insert Task 13
INSERT INTO Tasks (id, name, start_date, end_date, progress, status, stage, parent_task_id, supplier_id, job_id, date_created, last_updated)
VALUES (10012, 'Cabinets Installation', '2022-03-16', '2022-03-20', 0.0, 'CREATED', 'CABINETS', 10004, NULL, 10001, '2022-01-22T15:45:00Z', '2022-01-22T15:45:00Z');

-- Sample Insert Task 14
INSERT INTO Tasks (id, name, start_date, end_date, progress, status, stage, parent_task_id, supplier_id, job_id, date_created, last_updated)
VALUES (10013, 'PCI Planning', '2022-03-21', '2022-03-25', 0.0, 'CREATED', 'PCI', NULL, 10000, 10002, '2022-01-23T11:00:00Z', '2022-01-23T11:00:00Z');

-- Sample Insert Task 15
INSERT INTO Tasks (id, name, start_date, end_date, progress, status, stage, parent_task_id, supplier_id, job_id, date_created, last_updated)
VALUES (10014, 'PCI Inspection', '2022-03-26', '2022-03-30', 0.0, 'CREATED', 'PCI', 10013, 10001, 10001, '2022-01-24T09:30:00Z', '2022-01-24T09:30:00Z');

-- Sample Insert Task 16
INSERT INTO Tasks (id, name, start_date, end_date, progress, status, stage, parent_task_id, supplier_id, job_id, date_created, last_updated)
VALUES (10015, 'PCI Approval', '2022-04-01', '2022-04-05', 0.0, 'CREATED', 'PCI', 10003, NULL, 10002, '2022-01-25T12:00:00Z', '2022-01-25T12:00:00Z');

-- Sample Insert Task 17
INSERT INTO Tasks (id, name, start_date, end_date, progress, status, stage, parent_task_id, supplier_id, job_id, date_created, last_updated)
VALUES (10016, 'Final Touches', '2022-04-06', '2022-04-10', 0.0, 'CREATED', 'PCI', 10003, NULL, 10001, '2022-01-26T13:15:00Z', '2022-01-26T13:15:00Z');

-- Sample Insert Task 18
INSERT INTO Tasks (id, name, start_date, end_date, progress, status, stage, parent_task_id, supplier_id, job_id, date_created, last_updated)
VALUES (10017, 'Project Completion', '2022-04-11', '2022-04-15', 0.0, 'CREATED', 'PCI', 10002, NULL, 10002, '2022-01-27T10:45:00Z', '2022-01-27T10:45:00Z');

-- Sample Insert Task 19
INSERT INTO Tasks (id, name, start_date, end_date, progress, status, stage, parent_task_id, supplier_id, job_id, date_created, last_updated)
VALUES (10018, 'Task 18', '2022-04-16', '2022-04-20', 0.0, 'CREATED', 'LOCK_UP', NULL, 10002, 10001, '2022-01-28T09:30:00Z', '2022-01-28T09:30:00Z');

-- Sample Insert Task 20
INSERT INTO Tasks (id, name, start_date, end_date, progress, status, stage, parent_task_id, supplier_id, job_id, date_created, last_updated)
VALUES (10019, 'Task 19', '2022-04-21', '2022-04-25', 0.0, 'CREATED', 'LOCK_UP', NULL, 10003, 10002, '2022-01-29T14:00:00Z', '2022-01-29T14:00:00Z');

-- Sample Insert Task 21
INSERT INTO Tasks (id, name, start_date, end_date, progress, status, stage, parent_task_id, supplier_id, job_id, date_created, last_updated)
VALUES (10020, 'Task 20', '2022-04-26', '2022-04-30', 0.0, 'CREATED', 'LOCK_UP', NULL, 10004, 10001, '2022-01-30T11:15:00Z', '2022-01-30T11:15:00Z');
