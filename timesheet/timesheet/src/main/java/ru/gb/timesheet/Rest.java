package ru.gb.timesheet;

public class Rest {
    /*
     * HTTP - протокол
     * gRPC - протокол
     * WebSockets - протокол
     *
     * Путь/эндпоинт/ручка/ресурс - /timesheet
     *
     * REST - набор соглашений , как оформить/проектривать ресурсы вашего сервиса
     * 1. Не использовать глаголы в путях!!! - /deleteTimesheet/{id} - плохо
     *    DELETE/timesheet/{id} - хорошо
     * 2.Ресурсы должны вкладываться друг в друга
     * GET /user/{userId}/{roleId}
     *
     * 3. Рекоминдация: мо
     * множественное число для ресурсов
     *
     * 4.Слова внутри ресурса соединяются дефисом
     */
}
