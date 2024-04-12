package mattiaconsiglio.u5w2d5.controllers;

import mattiaconsiglio.u5w2d5.dtos.DeviceDTO;
import mattiaconsiglio.u5w2d5.dtos.NewDeviceDTO;
import mattiaconsiglio.u5w2d5.entities.Device;
import mattiaconsiglio.u5w2d5.exceptions.BadRequestException;
import mattiaconsiglio.u5w2d5.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/devices")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Device addDevice(@RequestBody @Validated NewDeviceDTO device, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException("Invalid data", validation.getAllErrors());
        }
        return deviceService.addDevice(device);
    }


    @GetMapping
    public Page<Device> getDevices(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "name") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return deviceService.getDevices(pageable);
    }


    @GetMapping("{id}")
    public Device getDevice(@PathVariable UUID id) {
        return deviceService.getDevice(id);
    }

    @PutMapping("{id}")
    public Device updateDevice(@PathVariable UUID id, @RequestBody @Validated DeviceDTO device, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException("Invalid data", validation.getAllErrors());
        }
        return deviceService.updateDevice(id, device);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDevice(@PathVariable UUID id) {
        deviceService.deleteDevice(id);
    }
}
