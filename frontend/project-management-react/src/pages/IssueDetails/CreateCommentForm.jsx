import { createComment } from "@/Redux/Comment/Action";
import { Avatar, AvatarFallback } from "@/components/ui/avatar";
import { Button } from "@/components/ui/button";
import { DialogClose } from "@/components/ui/dialog";
import {
  Form,
  FormField,
  FormItem,
  FormControl,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import React from "react";
import { useForm } from "react-hook-form";
import { useDispatch } from "react-redux";

const CreateCommentForm = ({ issueId }) => {
  const dispatch = useDispatch();

  const form = useForm({
    defaultValues: {
      content: "",
    },
  });

  const onSubmit = (data) => {
    dispatch(createComment({content: data.content, issueId}));
    console.log("Create project data ", data);}
  return (
    <div>
      <Form {...form}>
        <form className="flex gap-2" onSubmit={form.handleSubmit(onSubmit)}>
          <FormField
            control={form.control}
            name="content"
            render={({ field }) => (
              <FormItem>
                <div className="flex gap-2">
                  <div>
                    <Avatar>
                      <AvatarFallback>A</AvatarFallback>
                    </Avatar>
                  </div>
                  <FormControl>
                    <Input
                      {...field}
                      type="text"
                      className="w-[20rem]"
                      placeholder="Type a comment"
                    />
                  </FormControl>
                </div>

                <FormMessage />
              </FormItem>
            )}
          />
          <Button type="submit">Comment</Button>
        </form>
      </Form>
    </div>
  );
};

export default CreateCommentForm;
