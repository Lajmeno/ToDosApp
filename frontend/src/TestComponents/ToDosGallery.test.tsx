import { render, waitFor, screen } from "@testing-library/react";
import ToDosGallery from "../Components/ToDosGallery";




test("That Error is thrown when Status for Get-Request is not ok", async() =>{

    jest.spyOn(global, 'fetch').mockImplementation(() => {
        return Promise.resolve({
            status: 300
        } as Response);
    });

    render(<ToDosGallery />);

    await waitFor(() => {
        expect(screen.getByTestId("todos").textContent).toEqual("Could not GET Todos");
    });

})