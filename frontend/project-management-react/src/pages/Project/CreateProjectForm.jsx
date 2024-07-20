import { Button } from "@/components/ui/button";
import { DialogClose } from "@/components/ui/dialog";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import React from "react";
import { useForm } from "react-hook-form";
import { tags } from "../ProjectList/ProjectList";
import { Cross1Icon } from "@radix-ui/react-icons";

const CreateProjectForm = () => {

  const handleTagsChange = (newValue) => {
    const currentTags = form.getValues("tags");
    const updatedTags = currentTags.includes(newValue) ? 
    currentTags.filter(tag => tag !== newValue) : [...currentTags, newValue];

    form.setValue("tags", updatedTags)
  }

  const form = useForm({
    defaultValues: {
      name: "",
      description: "",
      category: "",
      tags: ["Javascript", "ReactJs"],
    },
  });

  const onSubmit = (data) => console.log("Create project data ", data);

  return (
    <div>
      <Form {...form}>
        <form className="space-y-5" onSubmit={form.handleSubmit(onSubmit)}>
          <FormField
            control={form.control}
            name="name"
            render={({ field }) => (
              <FormItem>
                <FormControl>
                  <Input
                    {...field}
                    type="text"
                    className="border w-full border-gray-700 py-5 px-5"
                    placeholder="Project name"
                  />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="description"
            render={({ field }) => (
              <FormItem>
                <FormControl>
                  <Input
                    {...field}
                    type="text"
                    className="border w-full border-gray-700 py-5 px-5"
                    placeholder="Project description"
                  />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="category"
            render={({ field }) => (
              <FormItem>
                <FormControl>
                  <Select
                    defaultValue="Fullstack"
                    value={field.value}
                    onValueChange={(value) => {
                      field.onChange(value);
                    }}

                    //className="border w-full border-gray-700 py-5 px-5"
                  >
                    <SelectTrigger className="w-full">
                      <SelectValue placeholder="Category" />
                    </SelectTrigger>
                    <SelectContent>
                      <SelectItem value="Fullstack">Fullstack</SelectItem>
                      <SelectItem value="Backend">Backend</SelectItem>
                      <SelectItem value="Frontend">Frontend</SelectItem>
                    </SelectContent>
                  </Select>
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="tags"
            render={({ field }) => (
              <FormItem>
                <FormControl>
                  <Select

                    onValueChange={(value) => {
                      handleTagsChange(value);
                    }}

                    //className="border w-full border-gray-700 py-5 px-5"
                  >
                    <SelectTrigger className="w-full">
                      <SelectValue placeholder="Tags" />
                    </SelectTrigger>
                    <SelectContent>
                      {tags.map((item) => (
                        <SelectItem key={item} value={item}>
                          {item}
                        </SelectItem>
                      ))}
                    </SelectContent>
                  </Select>
                </FormControl>
                <div className="flex gap-1 flex-wrap">

                      {field.value.map((item) => <div onClick={() => handleTagsChange(item)} className="cursor-pointer flex rounded-full items-center border gap-2 px-4 py-1">
                        <span className="text-sm">
                          {item}
                        </span>
                        <Cross1Icon className="h-3 w-3"/>
                      </div>)}
                      
                </div>
                <FormMessage />
              </FormItem>
            )}
          />
          <DialogClose>
            {false ? (
              <div>
                <p>
                  You can create only 3 project with free plan, please upgrade
                  your plan
                </p>
              </div>
            ) : (
              <Button className="w-full mt-5" type="submit">
                Create Project
              </Button>
            )}
          </DialogClose>
        </form>
      </Form>
    </div>
  );
};

export default CreateProjectForm;
